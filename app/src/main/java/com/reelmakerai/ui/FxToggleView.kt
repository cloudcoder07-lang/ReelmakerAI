package com.reelmakerai.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.ToggleButton
import androidx.appcompat.widget.AppCompatToggleButton

class FxToggleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatToggleButton(context, attrs) {

    var effectType: com.reelmakerai.audio.EffectType? = null
}
