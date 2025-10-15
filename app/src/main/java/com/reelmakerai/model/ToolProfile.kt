package com.reelmakerai.model

enum class ToolProfile {
    CANVAS_ROTATE,
    CANVAS_CROP,
    AUDIO_VOLUME,
    EFFECT_FILTER,
    AI_SMARTCROP
}

data class ToolUiConfig(
    val showVideo: Boolean,
    val showOverlay: Boolean
)

val toolUiProfiles = mapOf(
    ToolProfile.CANVAS_ROTATE to ToolUiConfig(showVideo = true, showOverlay = false),
    ToolProfile.CANVAS_CROP to ToolUiConfig(showVideo = true, showOverlay = true),
    ToolProfile.AUDIO_VOLUME to ToolUiConfig(showVideo = true, showOverlay = true),
    ToolProfile.EFFECT_FILTER to ToolUiConfig(showVideo = true, showOverlay = true),
    ToolProfile.AI_SMARTCROP to ToolUiConfig(showVideo = true, showOverlay = true)
)
