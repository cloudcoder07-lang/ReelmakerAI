package com.reelmakerai.model

import android.content.Context
import com.reelmakerai.R

enum class ToolType(val iconRes: Int) {
    CANVAS(R.drawable.ic_canvas),
    AUDIO(R.drawable.ic_audio),
    STICKER(R.drawable.ic_sticker),
    TEXT(R.drawable.ic_text),
    EFFECT(R.drawable.ic_effect),
    FILTER(R.drawable.ic_filter),
    VOICE_FX(R.drawable.ic_voice_fx),
    CUT(R.drawable.ic_cut),
    MERGE(R.drawable.ic_merge);

    fun getSubTools(context: Context): List<SubToolItem> {
        return when (this) {
            CANVAS -> listOf(
                SubToolItem("Resize", R.drawable.ic_resize),
                SubToolItem("Crop", R.drawable.ic_crop),
                SubToolItem("Rotate", R.drawable.ic_rotate),
                SubToolItem("Zoom", R.drawable.ic_zoom),
                SubToolItem("BGFill", R.drawable.ic_background)
            )
            AUDIO -> listOf(
                SubToolItem("Music", R.drawable.ic_music),
                SubToolItem("Voice", R.drawable.ic_voiceover),
                SubToolItem("FX", R.drawable.ic_soundfx),
                SubToolItem("Volume", R.drawable.ic_volume),
                SubToolItem("Sync", R.drawable.ic_beat)
            )
            STICKER -> listOf(
                SubToolItem("Emoji", R.drawable.ic_emoji),
                SubToolItem("Animate", R.drawable.ic_animated),
                SubToolItem("Brand", R.drawable.ic_brand),
                SubToolItem("Upload", R.drawable.ic_upload),
                SubToolItem("Scale", R.drawable.ic_position)
            )
            TEXT -> listOf(
                SubToolItem("Text", R.drawable.ic_text),
                SubToolItem("Font", R.drawable.ic_font),
                SubToolItem("Color", R.drawable.ic_color),
                SubToolItem("Motion", R.drawable.ic_animation),
                SubToolItem("Time", R.drawable.ic_timing)
            )
            EFFECT -> listOf(
                SubToolItem("Glitch", R.drawable.ic_glitch),
                SubToolItem("Blur", R.drawable.ic_blur),
                SubToolItem("Shake", R.drawable.ic_shake),
                SubToolItem("VHS", R.drawable.ic_vhs),
                SubToolItem("Sparkle", R.drawable.ic_sparkle),
                SubToolItem("FX+", R.drawable.ic_customfx)
            )
            FILTER -> listOf(
                SubToolItem("Mood", R.drawable.ic_mood),
                SubToolItem("LUTs", R.drawable.ic_lut),
                SubToolItem("Intensity", R.drawable.ic_intensity),
                SubToolItem("Grid", R.drawable.ic_grid)
            )
            VOICE_FX -> listOf(
                SubToolItem("Robot", R.drawable.ic_robot),
                SubToolItem("Chipmunk", R.drawable.ic_chipmunk),
                SubToolItem("Deep", R.drawable.ic_deepvoice),
                SubToolItem("Echo", R.drawable.ic_echo),
                SubToolItem("Reverb", R.drawable.ic_reverb),
                SubToolItem("Pitch", R.drawable.ic_pitch)
            )
            CUT -> listOf(
                SubToolItem("Trim", R.drawable.ic_trim),
                SubToolItem("Split", R.drawable.ic_split),
                SubToolItem("MultiCut", R.drawable.ic_multicut),
                SubToolItem("Ripple", R.drawable.ic_ripple),
                SubToolItem("Preview", R.drawable.ic_previewcut)
            )
            MERGE -> listOf(
                SubToolItem("Video", R.drawable.ic_addvideo),
                SubToolItem("Photo", R.drawable.ic_addphoto),
                SubToolItem("Insert", R.drawable.ic_insert),
                SubToolItem("Append", R.drawable.ic_append),
                SubToolItem("Transitions", R.drawable.ic_transition)
            )
        }
    }
}

// 🔧 Extension function to map ToolType to ToolProfile
fun ToolType.toProfile(): ToolProfile = when (this) {
    ToolType.CANVAS -> ToolProfile.CANVAS_ROTATE
    ToolType.AUDIO -> ToolProfile.AUDIO_VOLUME
    ToolType.STICKER -> ToolProfile.CANVAS_CROP
    ToolType.TEXT -> ToolProfile.CANVAS_CROP
    ToolType.EFFECT -> ToolProfile.EFFECT_FILTER
    ToolType.FILTER -> ToolProfile.EFFECT_FILTER
    ToolType.VOICE_FX -> ToolProfile.AUDIO_VOLUME
    ToolType.CUT -> ToolProfile.CANVAS_CROP
    ToolType.MERGE -> ToolProfile.CANVAS_ROTATE
}
