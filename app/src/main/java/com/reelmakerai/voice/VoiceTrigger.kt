package com.reelmakerai.voice

object VoiceTrigger {
    init {
        System.loadLibrary("reelmaker_native")
    }

    external fun detectKeyword(audioBuffer: FloatArray): Boolean
}
