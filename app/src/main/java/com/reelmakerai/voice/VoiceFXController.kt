package com.reelmakerai.voice

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import androidx.core.content.ContextCompat
import kotlinx.coroutines.*

class VoiceFXController(
    private val context: Context,
    private val onCommandDetected: (String) -> Unit
) {

    private val engine = VoiceTriggerEngine(context)
    private val scope = CoroutineScope(Dispatchers.Default)
    private var isListening = false
    private var recorder: AudioRecord? = null

    fun startListening() {
        if (isListening) return

        recorder = createAudioRecordSafely()
        if (recorder == null) return

        isListening = true
        recorder?.startRecording()

        scope.launch {
            val buffer = ShortArray(16000)
            while (isListening) {
                val read = recorder?.read(buffer, 0, buffer.size) ?: 0
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
        recorder?.stop()
        recorder?.release()
        recorder = null
    }

    private fun createAudioRecordSafely(): AudioRecord? {
        return try {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                AudioRecord(
                    MediaRecorder.AudioSource.MIC,
                    16000,
                    AudioFormat.CHANNEL_IN_MONO,
                    AudioFormat.ENCODING_PCM_16BIT,
                    16000 * 2
                )
            } else {
                null
            }
        } catch (e: SecurityException) {
            null
        }
    }
}
