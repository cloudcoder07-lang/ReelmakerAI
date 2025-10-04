package com.reelmakerai.voice

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import androidx.core.content.ContextCompat

class VoiceCommandManager(private val context: Context) {

    private val bufferSize = AudioRecord.getMinBufferSize(
        16000,
        AudioFormat.CHANNEL_IN_MONO,
        AudioFormat.ENCODING_PCM_16BIT
    )


    private val audioRecord: AudioRecord? = createAudioRecord()

    private fun createAudioRecord(): AudioRecord? {
        return if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            AudioRecord(
                MediaRecorder.AudioSource.MIC,
                16000,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufferSize
            )
        } else {
            null
        }
    }

    fun startListening() {
        audioRecord?.startRecording()
    }

    fun readAudio(buffer: ShortArray): Int {
        return audioRecord?.read(buffer, 0, buffer.size) ?: 0
    }

    fun stopListening() {
        audioRecord?.let {
            it.stop()
            it.release()
        }
    }
}
