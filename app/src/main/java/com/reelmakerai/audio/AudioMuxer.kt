package com.reelmakerai.audio

import android.media.MediaCodec
import android.media.MediaExtractor
import android.media.MediaFormat
import android.media.MediaMuxer
import java.io.File

class AudioMuxer {

    fun muxAudioVideo(videoFile: File, audioFile: File, outputFile: File): Boolean {
        val muxer = MediaMuxer(outputFile.absolutePath, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4)

        val videoExtractor = MediaExtractor()
        videoExtractor.setDataSource(videoFile.absolutePath)
        val videoTrackIndex = selectTrack(videoExtractor, "video/")
        val videoFormat = videoExtractor.getTrackFormat(videoTrackIndex)
        val muxVideoTrack = muxer.addTrack(videoFormat)

        val audioExtractor = MediaExtractor()
        audioExtractor.setDataSource(audioFile.absolutePath)
        val audioTrackIndex = selectTrack(audioExtractor, "audio/")
        val audioFormat = audioExtractor.getTrackFormat(audioTrackIndex)
        val muxAudioTrack = muxer.addTrack(audioFormat)

        muxer.start()

        writeSampleData(videoExtractor, muxer, muxVideoTrack)
        writeSampleData(audioExtractor, muxer, muxAudioTrack)

        muxer.stop()
        muxer.release()
        videoExtractor.release()
        audioExtractor.release()

        return true
    }

    private fun selectTrack(extractor: MediaExtractor, mimePrefix: String): Int {
        for (i in 0 until extractor.trackCount) {
            val format = extractor.getTrackFormat(i)
            val mime = format.getString(MediaFormat.KEY_MIME) ?: continue
            if (mime.startsWith(mimePrefix)) {
                extractor.selectTrack(i)
                return i
            }
        }
        throw IllegalArgumentException("No $mimePrefix track found")
    }

    private fun writeSampleData(extractor: MediaExtractor, muxer: MediaMuxer, trackIndex: Int) {
        val bufferSize = 256 * 1024
        val buffer = java.nio.ByteBuffer.allocate(bufferSize)
        val bufferInfo = MediaCodec.BufferInfo()

        while (true) {
            val sampleSize = extractor.readSampleData(buffer, 0)
            if (sampleSize < 0) break

            bufferInfo.offset = 0
            bufferInfo.size = sampleSize
            bufferInfo.presentationTimeUs = extractor.sampleTime
            bufferInfo.flags = when {
                extractor.sampleFlags and MediaExtractor.SAMPLE_FLAG_SYNC != 0 -> MediaCodec.BUFFER_FLAG_SYNC_FRAME
                extractor.sampleFlags and MediaExtractor.SAMPLE_FLAG_PARTIAL_FRAME != 0 -> MediaCodec.BUFFER_FLAG_PARTIAL_FRAME
                else -> 0
            }

            muxer.writeSampleData(trackIndex, buffer, bufferInfo)
            extractor.advance()
        }
    }
}
