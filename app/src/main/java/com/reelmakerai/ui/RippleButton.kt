package com.reelmakerai.ui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.reelmakerai.R

class RippleButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatButton(context, attrs) {

    init {
        background = context.getDrawable(R.drawable.ripple_background)
    }
}
