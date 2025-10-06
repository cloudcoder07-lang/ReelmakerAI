package com.reelmakerai.audio

import android.util.Log

object VoiceFxProcessorCore {

    fun applyEffect(input: ByteArray, type: EffectType): ByteArray {
        Log.d("VoiceFxProcessor", "Applying effect: ${type.name}")
        // Placeholder: return input unchanged
        return input
    }
}
