package com.reelmakerai.audio

import android.media.AudioManager
import android.media.AudioTrack

object AudioRouter {

    fun configureOutput(track: AudioTrack, useSpeaker: Boolean) {
        track.setStereoVolume(
            if (useSpeaker) 1.0f else 0.5f,
            if (useSpeaker) 1.0f else 0.5f
        )
    }
    fun applyEffect(input: ShortArray, type: EffectType): ShortArray {
        return when (type) {
            EffectType.ROBOT -> input.map { (it * 0.8).toShort() }.toShortArray()
            EffectType.ECHO -> input.mapIndexed { i, s -> (s + if (i > 1000) input[i - 1000] / 2 else 0).toShort() }.toShortArray()
            EffectType.CHIPMUNK -> input.filterIndexed { i, _ -> i % 2 == 0 }.toShortArray()
            EffectType.DEEP -> input.map { (it * 1.2).toShort() }.toShortArray()
        }
    }

}
