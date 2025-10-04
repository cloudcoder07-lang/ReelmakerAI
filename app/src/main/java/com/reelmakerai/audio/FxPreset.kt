package com.reelmakerai.audio

data class FxPreset(
    val name: String,
    val iconResId: Int,
    val effectType: EffectType
)

enum class EffectType {
    ROBOT, ECHO, CHIPMUNK, DEEP
}
