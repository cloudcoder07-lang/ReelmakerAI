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
            CANVAS -> listOf(SubToolItem("Resize") { /* TODO */ })
            AUDIO -> listOf(SubToolItem("Volume") { /* TODO */ })
            STICKER -> listOf(SubToolItem("Add Sticker") { /* TODO */ })
            TEXT -> listOf(SubToolItem("Font") { /* TODO */ })
            EFFECT -> listOf(SubToolItem("Sparkle") { /* TODO */ })
            FILTER -> listOf(SubToolItem("Color Tone") { /* TODO */ })
            VOICE_FX -> listOf(SubToolItem("Echo") { /* TODO */ })
            CUT -> listOf(SubToolItem("Trim") { /* TODO */ })
            MERGE -> listOf(SubToolItem("Join") { /* TODO */ })
        }
    }
}
