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
                SubToolItem("Resize", R.drawable.ic_resize,context.getString(R.string.canvas_resize)),
                SubToolItem(name = "Crop", iconRes = R.drawable.ic_crop, label = context.getString(R.string.canvas_crop)),
                SubToolItem("Rotate", R.drawable.ic_rotate, context.getString(R.string.canvas_rotate)),
                SubToolItem("Zoom", R.drawable.ic_zoom, context.getString(R.string.canvas_zoom)),
                SubToolItem("BGFill", R.drawable.ic_background, context.getString(R.string.canvas_bg_fill))
            )
            AUDIO -> listOf(
                SubToolItem("Music", R.drawable.ic_music, context.getString(R.string.audio_add_music)),
                SubToolItem("Voice", R.drawable.ic_voiceover, context.getString(R.string.audio_voiceover)),
                SubToolItem("FX", R.drawable.ic_soundfx, context.getString(R.string.audio_sound_effects)),
                SubToolItem("Volume", R.drawable.ic_volume, context.getString(R.string.audio_volume_control)),
                SubToolItem("Sync", R.drawable.ic_beat, context.getString(R.string.audio_sync_to_beat))
            )
            STICKER -> listOf(
                SubToolItem("Emoji", R.drawable.ic_emoji, context.getString(R.string.sticker_emoji_pack)),
                SubToolItem("Animate", R.drawable.ic_animated, context.getString(R.string.sticker_animated_stickers)),
                SubToolItem("Brand", R.drawable.ic_brand,  context.getString(R.string.sticker_branded_overlays)),
                SubToolItem("Upload", R.drawable.ic_upload, context.getString(R.string.sticker_custom_upload) ),
                SubToolItem("Scale", R.drawable.ic_position, context.getString(R.string.sticker_position_scale))
            )
            TEXT -> listOf(
                SubToolItem("Text", R.drawable.ic_text, context.getString(R.string.text_add_text)),
                SubToolItem("Font", R.drawable.ic_font, context.getString(R.string.text_font_picker)),
                SubToolItem("Color", R.drawable.ic_color, context.getString(R.string.text_color_gradient)),
                SubToolItem("Motion", R.drawable.ic_animation, context.getString(R.string.text_animation)),
                SubToolItem("Time", R.drawable.ic_timing, context.getString(R.string.text_timing))
            )
            EFFECT -> listOf(
                SubToolItem("Glitch", R.drawable.ic_glitch, context.getString(R.string.effect_glitch)),
                SubToolItem("Blur", R.drawable.ic_blur, context.getString(R.string.effect_blur)),
                SubToolItem("Shake", R.drawable.ic_shake, context.getString(R.string.effect_shake)),
                SubToolItem("VHS", R.drawable.ic_vhs, context.getString(R.string.effect_vhs)),
                SubToolItem("Sparkle", R.drawable.ic_sparkle, context.getString(R.string.effect_sparkle)),
                SubToolItem("FX+", R.drawable.ic_customfx, context.getString(R.string.effect_custom_fx))
            )
            FILTER -> listOf(
                SubToolItem("Mood", R.drawable.ic_mood, context.getString(R.string.filter_mood_presets)),
                SubToolItem("LUTs", R.drawable.ic_lut, context.getString(R.string.filter_luts)),
                SubToolItem("Intensity", R.drawable.ic_intensity, context.getString(R.string.filter_intensity)),
                SubToolItem("Grid", R.drawable.ic_grid, context.getString(R.string.filter_preview_grid))
            )
            VOICE_FX -> listOf(
                SubToolItem("Robot", R.drawable.ic_robot,  context.getString(R.string.voice_fx_robot)),
                SubToolItem("Chipmunk", R.drawable.ic_chipmunk, context.getString(R.string.voice_fx_chipmunk)),
                SubToolItem("Deep", R.drawable.ic_deepvoice, context.getString(R.string.voice_fx_deep_voice)),
                SubToolItem("Echo", R.drawable.ic_echo, context.getString(R.string.voice_fx_echo)),
                SubToolItem("Reverb", R.drawable.ic_reverb, context.getString(R.string.voice_fx_reverb)),
                SubToolItem("Pitch", R.drawable.ic_pitch, context.getString(R.string.voice_fx_pitch))
            )
            CUT -> listOf(
                SubToolItem("Trim", R.drawable.ic_trim, context.getString(R.string.cut_trim)),
                SubToolItem("Split", R.drawable.ic_split, context.getString(R.string.cut_split)),
                SubToolItem("MultiCut", R.drawable.ic_multicut, context.getString(R.string.cut_multicut)),
                SubToolItem("Ripple", R.drawable.ic_ripple, context.getString(R.string.cut_ripple)),
                SubToolItem("Preview", R.drawable.ic_previewcut, context.getString(R.string.cut_preview) )
            )
            MERGE -> listOf(
                SubToolItem("Video", R.drawable.ic_addvideo, context.getString(R.string.merge_add_video)),
                SubToolItem("Photo", R.drawable.ic_addphoto, context.getString(R.string.merge_add_photo)),
                SubToolItem("Insert", R.drawable.ic_insert, context.getString(R.string.merge_insert)),
                SubToolItem("Append", R.drawable.ic_append, context.getString(R.string.merge_append)),
                SubToolItem("Transitions", R.drawable.ic_transition, context.getString(R.string.merge_transitions))
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
