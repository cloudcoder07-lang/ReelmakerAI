package com.reelmakerai.export.engine

import android.media.*
import java.io.File
import java.nio.ByteBuffer


object AudioMuxer {

    fun muxAudio(originalVideoPath: String, silentVideoPath: String, outputPath: String) {
        val extractor = MediaExtractor()
        extractor.setDataSource(originalVideoPath)

        val audioTrackIndex = (0 until extractor.trackCount).firstOrNull {
            extractor.getTrackFormat(it).getString(MediaFormat.KEY_MIME)?.startsWith("audio/") == true
        } ?: return

        extractor.selectTrack(audioTrackIndex)
        val audioFormat = extractor.getTrackFormat(audioTrackIndex)

        val muxer = MediaMuxer(outputPath, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4)
        val videoExtractor = MediaExtractor()
        videoExtractor.setDataSource(silentVideoPath)

        val videoTrackIndex = (0 until videoExtractor.trackCount).first {
            videoExtractor.getTrackFormat(it).getString(MediaFormat.KEY_MIME)?.startsWith("video/") == true
        }
        videoExtractor.selectTrack(videoTrackIndex)
        val videoFormat = videoExtractor.getTrackFormat(videoTrackIndex)

        val muxVideoIndex = muxer.addTrack(videoFormat)
        val muxAudioIndex = muxer.addTrack(audioFormat)
        muxer.start()

        fun copyTrack(extractor: MediaExtractor, muxIndex: Int) {
            val buffer = ByteArray(1024 * 1024)
            val info = MediaCodec.BufferInfo()
            while (true) {
                val sampleSize = extractor.readSampleData(ByteBuffer.wrap(buffer), 0)
                if (sampleSize < 0) break
                info.offset = 0
                info.size = sampleSize
                info.presentationTimeUs = extractor.sampleTime
                info.flags = extractor.sampleFlags
                muxer.writeSampleData(muxIndex, ByteBuffer.wrap(buffer, 0, sampleSize), info)
                extractor.advance()
            }
        }

        copyTrack(videoExtractor, muxVideoIndex)
        copyTrack(extractor, muxAudioIndex)

        muxer.stop()
        muxer.release()
        extractor.release()
        videoExtractor.release()
    }
}
