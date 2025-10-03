package com.reelmakerai.audio

object VoiceFxProcessor {

    fun applyRobotEffect(input: ShortArray): ShortArray {
        return input.mapIndexed { i, sample ->
            val modulated = sample * Math.sin(2 * Math.PI * i / 30).toFloat()
            modulated.toInt().coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
        }.toShortArray()
    }

    // TODO: Add pitch shift, echo, dramatic FX
}