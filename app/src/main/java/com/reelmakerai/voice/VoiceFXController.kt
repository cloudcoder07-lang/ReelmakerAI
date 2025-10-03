package com.reelmakerai.voice

import android.content.Context
import android.media.AudioRecord
import android.media.MediaRecorder
import android.media.AudioFormat
import kotlinx.coroutines.*

class VoiceFXController(context: Context, val onCommandDetected: (String) -> Unit) {

    private val engine = VoiceTriggerEngine(context)
    private val scope = CoroutineScope(Dispatchers.Default)
    private var isListening = false

    private val recorder = AudioRecord(
        MediaRecorder.AudioSource.MIC,
        16000,
        AudioFormat.CHANNEL_IN_MONO,
        AudioFormat.ENCODING_PCM_16BIT,
        16000 * 2
    )

    fun startListening() {
        if (isListening) return
        isListening = true
        recorder.startRecording()

        scope.launch {
            val buffer = ShortArray(16000)
            while (isListening) {
                val read = recorder.read(buffer, 0, buffer.size)
                val floatBuffer = buffer.map { it / 32767f }.toFloatArray()
                val command = engine.detectCommand(floatBuffer)
                if (command != null) {
                    withContext(Dispatchers.Main) {
                        onCommandDetected(command)
                    }
                }
                delay(500)
            }
        }
    }

    fun stopListening() {
        isListening = false
        recorder.stop()
    }
}
