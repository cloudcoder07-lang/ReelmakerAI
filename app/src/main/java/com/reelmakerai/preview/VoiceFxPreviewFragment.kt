package com.reelmakerai.preview

import android.media.AudioRecord
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reelmakerai.audio.EffectType
import com.reelmakerai.audio.VoiceFxProcessorCore
import com.reelmakerai.audio.AudioRouter
import com.reelmakerai.R

class VoiceFxPreviewFragment : Fragment() {

    private lateinit var recorder: AudioRecord
    private var currentEffect: EffectType = EffectType.None
    private val buffer = ByteArray(2048)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_voice_fx_preview, container, false)
    }

    override fun onStart() {
        super.onStart()
        recorder = AudioRouter.createRecorder(requireContext()) ?: return
        recorder.startRecording()
        startPreviewLoop()
    }

    private fun startPreviewLoop() {
        Thread {
            while (!Thread.interrupted()) {
                val read = recorder.read(buffer, 0, buffer.size)
                if (read > 0) {
                    val input = buffer.copyOf(read)
                    val output = VoiceFxProcessorCore.applyEffect(input, currentEffect)
                    Log.d("VoiceFxPreview", "Applied ${currentEffect.name} to $read bytes")
                }
            }
        }.start()
    }

    fun setEffect(effect: EffectType) {
        currentEffect = effect
    }

    override fun onStop() {
        super.onStop()
        recorder.stop()
        recorder.release()
    }
}
