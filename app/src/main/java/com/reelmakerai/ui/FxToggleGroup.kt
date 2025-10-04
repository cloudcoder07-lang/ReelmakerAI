package com.reelmakerai.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.reelmakerai.audio.EffectType

class FxToggleGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    var onEffectSelected: ((EffectType) -> Unit)? = null

    fun bindPresets(presets: List<EffectType>) {
        removeAllViews()
        presets.forEach { type ->
            val toggle = FxToggleView(context).apply {
                text = type.name
                effectType = type
                setOnClickListener {
                    onEffectSelected?.invoke(type)
                }
            }
            addView(toggle)
        }
    }
}
