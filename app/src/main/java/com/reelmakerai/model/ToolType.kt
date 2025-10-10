package com.reelmakerai.model

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

    fun getSubTools(): List<SubToolItem> {
        return when (this) {
            CANVAS -> listOf(
                SubToolItem("Resize", R.drawable.ic_resize),
                SubToolItem("Crop", R.drawable.ic_crop),
                SubToolItem("Rotate", R.drawable.ic_rotate),
                SubToolItem("Zoom/Pan", R.drawable.ic_zoom),
                SubToolItem("Background Fill", R.drawable.ic_background)
            )
            AUDIO -> listOf(
                SubToolItem("Add Music", R.drawable.ic_music),
                SubToolItem("Voiceover", R.drawable.ic_voiceover),
                SubToolItem("Sound Effects", R.drawable.ic_soundfx),
                SubToolItem("Volume Control", R.drawable.ic_volume),
                SubToolItem("Sync to Beat", R.drawable.ic_beat)
            )
            STICKER -> listOf(
                SubToolItem("Emoji Pack", R.drawable.ic_emoji),
                SubToolItem("Animated Stickers", R.drawable.ic_animated),
                SubToolItem("Branded Overlays", R.drawable.ic_brand),
                SubToolItem("Custom Upload", R.drawable.ic_upload),
                SubToolItem("Position & Scale", R.drawable.ic_position)
            )
            TEXT -> listOf(
                SubToolItem("Add Text", R.drawable.ic_text),
                SubToolItem("Font Picker", R.drawable.ic_font),
                SubToolItem("Color & Gradient", R.drawable.ic_color),
                SubToolItem("Animation", R.drawable.ic_animation),
                SubToolItem("Timing", R.drawable.ic_timing)
            )
            EFFECT -> listOf(
                SubToolItem("Glitch", R.drawable.ic_glitch),
                SubToolItem("Blur", R.drawable.ic_blur),
                SubToolItem("Shake", R.drawable.ic_shake),
                SubToolItem("VHS", R.drawable.ic_vhs),
                SubToolItem("Sparkle", R.drawable.ic_sparkle),
                SubToolItem("Custom FX", R.drawable.ic_customfx)
            )
            FILTER -> listOf(
                SubToolItem("Mood Presets", R.drawable.ic_mood),
                SubToolItem("LUTs", R.drawable.ic_lut),
                SubToolItem("Intensity Slider", R.drawable.ic_intensity),
                SubToolItem("Preview Grid", R.drawable.ic_grid)
            )
            VOICE_FX -> listOf(
                SubToolItem("Robot", R.drawable.ic_robot),
                SubToolItem("Chipmunk", R.drawable.ic_chipmunk),
                SubToolItem("Deep Voice", R.drawable.ic_deepvoice),
                SubToolItem("Echo", R.drawable.ic_echo),
                SubToolItem("Reverb", R.drawable.ic_reverb),
                SubToolItem("Custom Pitch", R.drawable.ic_pitch)
            )
            CUT -> listOf(
                SubToolItem("Trim Start/End", R.drawable.ic_trim),
                SubToolItem("Split at Playhead", R.drawable.ic_split),
                SubToolItem("Multi-Segment Cut", R.drawable.ic_multicut),
                SubToolItem("Ripple Delete", R.drawable.ic_ripple),
                SubToolItem("Preview Before Cut", R.drawable.ic_previewcut)
            )
            MERGE -> listOf(
                SubToolItem("Add Video", R.drawable.ic_addvideo),
                SubToolItem("Add Photo", R.drawable.ic_addphoto),
                SubToolItem("Insert at Playhead", R.drawable.ic_insert),
                SubToolItem("Append to End", R.drawable.ic_append),
                SubToolItem("Transition Options", R.drawable.ic_transition)
            )
        }
    }
}
