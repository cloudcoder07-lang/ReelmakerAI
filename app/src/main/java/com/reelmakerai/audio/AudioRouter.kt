package com.reelmakerai.audio

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import androidx.core.content.ContextCompat

object AudioRouter {

    fun createRecorder(context: Context, sampleRate: Int = 44100): AudioRecord? {
        val permission = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            return null // Or throw SecurityException if you want to handle it upstream
        }

        val bufferSize = AudioRecord.getMinBufferSize(
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )

        return AudioRecord(
            MediaRecorder.AudioSource.MIC,
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize
        )
    }

    fun convertToPcm16(input: FloatArray): ShortArray {
        return input.map { (it * Short.MAX_VALUE.toInt()).toInt().toShort() }.toShortArray()
    }
}
