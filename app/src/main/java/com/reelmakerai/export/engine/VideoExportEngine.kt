package com.reelmakerai.export.engine

import android.content.Context
import android.graphics.*
import android.media.*
import android.view.Surface
import com.reelmakerai.export.ExportCallback
import com.reelmakerai.export.ExportTask
import com.reelmakerai.overlay.OverlayManager
import java.io.File
import java.nio.ByteBuffer

object VideoExportEngine {

    fun export(context: Context, task: ExportTask, callback: ExportCallback) {
        Thread {
            try {
                val extractor = MediaExtractor()
                extractor.setDataSource(task.inputPath)

                val trackIndex = (0 until extractor.trackCount).first {
                    extractor.getTrackFormat(it).getString(MediaFormat.KEY_MIME)?.startsWith("video/") == true
                }

                extractor.selectTrack(trackIndex)
                val inputFormat = extractor.getTrackFormat(trackIndex)

                val width = inputFormat.getInteger(MediaFormat.KEY_WIDTH)
                val height = inputFormat.getInteger(MediaFormat.KEY_HEIGHT)

                val outputFormat = MediaFormat.createVideoFormat("video/avc", width, height).apply {
                    setInteger(MediaFormat.KEY_BIT_RATE, if (task.exportHD) 5_000_000 else 1_000_000)
                    setInteger(MediaFormat.KEY_FRAME_RATE, 30)
                    setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 1)
                    setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface)
                }

                val encoder = MediaCodec.createEncoderByType("video/avc")
                encoder.configure(outputFormat, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE)
                val encoderSurface: Surface = encoder.createInputSurface()
                encoder.start()


                val decoder = MediaCodec.createDecoderByType(inputFormat.getString(MediaFormat.KEY_MIME)!!)
                decoder.configure(inputFormat, encoderSurface, null, 0)
                decoder.start()

                val bufferInfo = MediaCodec.BufferInfo()
                var isEOS = false
                var frameCount = 0

                val paint = Paint(Paint.ANTI_ALIAS_FLAG)

                while (!isEOS) {
                    val inputBufferIndex = decoder.dequeueInputBuffer(10000)
                    if (inputBufferIndex >= 0) {
                        val inputBuffer: ByteBuffer = decoder.getInputBuffer(inputBufferIndex)!!
                        val sampleSize = extractor.readSampleData(inputBuffer, 0)
                        if (sampleSize < 0) {
                            decoder.queueInputBuffer(inputBufferIndex, 0, 0, 0L, MediaCodec.BUFFER_FLAG_END_OF_STREAM)
                            isEOS = true
                        } else {
                            val presentationTimeUs = extractor.sampleTime
                            decoder.queueInputBuffer(inputBufferIndex, 0, sampleSize, presentationTimeUs, 0)
                            extractor.advance()
                        }
                    }

                    val outputBufferIndex = decoder.dequeueOutputBuffer(bufferInfo, 10000)
                    if (outputBufferIndex >= 0) {
                        decoder.releaseOutputBuffer(outputBufferIndex, true)

                        // Create a blank canvas
                        val frameBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                        val canvas = Canvas(frameBitmap)

                        // 🔹 Apply LUT (placeholder logic)
                        val lutPaint = Paint()
                        lutPaint.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(1.2f) })
                        canvas.drawBitmap(frameBitmap, 0f, 0f, lutPaint)

                        // 🔹 Render overlays
                        OverlayManager.renderOverlays(canvas, task.overlays)

                        // 🔹 Apply watermark if available
                        task.watermarkBitmap?.let { watermark ->
                            val scaled = Bitmap.createScaledBitmap(watermark, width / 5, height / 5, true)
                            val left = width - scaled.width - 16
                            val top = height - scaled.height - 16
                            canvas.drawBitmap(scaled, left.toFloat(), top.toFloat(), paint)
                        }

                        // TODO: Encode frameBitmap into video stream

                        frameCount++
                        val progress = (frameCount * 100 / 300).coerceAtMost(100)
                        callback.onProgress(progress)
                    }
                }

                encoder.stop()
                encoder.release()
                decoder.stop()
                decoder.release()

                callback.onComplete(File(task.outputPath))

            } catch (e: Exception) {
                callback.onError("Export failed: ${e.message}")
            }
        }.start()
    }
}
