package com.reelmakerai.preview

import android.media.*
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.reelmakerai.audio.VoiceFxProcessor
import kotlinx.coroutines.*

class `VoiceFxPreviewFragment-old` : Fragment() {

    private val sampleRate = 16000
    private val bufferSize = AudioRecord.getMinBufferSize(
        sampleRate,
        AudioFormat.CHANNEL_IN_MONO,
        AudioFormat.ENCODING_PCM_16BIT
    )

    private var audioRecord: AudioRecord? = null
    private var audioTrack: AudioTrack? = null
    private var recordingJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        audioRecord = createAudioRecordSafely()
        audioTrack = AudioTrack(
            AudioManager.STREAM_MUSIC,
            sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize,
            AudioTrack.MODE_STREAM
        )

        startPreview()
    }

    private fun startPreview() {
        recordingJob = CoroutineScope(Dispatchers.IO).launch {
            val buffer = ShortArray(bufferSize)
            audioRecord?.startRecording()
            audioTrack?.play()

            while (isActive) {
                val read = audioRecord?.read(buffer, 0, buffer.size) ?: 0
                val fx = VoiceFxProcessor.applyRobotEffect(buffer.copyOf(read))
                audioTrack?.write(fx, 0, fx.size)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recordingJob?.cancel()
        audioRecord?.stop()
        audioRecord?.release()
        audioTrack?.stop()
        audioTrack?.release()
    }

    private fun createAudioRecordSafely(): AudioRecord? {
        return try {
            AudioRecord(
                MediaRecorder.AudioSource.MIC,
                sampleRate,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufferSize
            )
        } catch (e: SecurityException) {
            null
        }
    }
}
