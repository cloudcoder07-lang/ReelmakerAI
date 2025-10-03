package com.reelmakerai.voice

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder

class VoiceFXManager {

    private val bufferSize = AudioRecord.getMinBufferSize(
        16000,
        AudioFormat.CHANNEL_IN_MONO,
        AudioFormat.ENCODING_PCM_16BIT
    )

    private val audioRecord = AudioRecord(
        MediaRecorder.AudioSource.MIC,
        16000,
        AudioFormat.CHANNEL_IN_MONO,
        AudioFormat.ENCODING_PCM_16BIT,
        bufferSize
    )

    fun startListening(onTrigger: () -> Unit) {
        audioRecord.startRecording()
        Thread {
            val buffer = ShortArray(bufferSize)
            while (true) {
                val read = audioRecord.read(buffer, 0, buffer.size)
                val floatBuffer = buffer.map { it / 32768f }.toFloatArray()
                val triggered = VoiceTrigger.detectKeyword(floatBuffer)
                if (triggered) onTrigger()
            }
        }.start()
    }

    fun stopListening() {
        audioRecord.stop()
        audioRecord.release()
    }
}
