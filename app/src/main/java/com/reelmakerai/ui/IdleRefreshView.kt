package com.reelmakerai.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import android.widget.FrameLayout
import android.os.Handler
import android.os.Looper

class IdleRefreshView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val tips = listOf(
        "Try the Epic preset for dramatic tone",
        "Voice FX adds cinematic punch",
        "Unlock Pro for watermark-free exports"
    )

    private val textView = TextView(context).apply {
        textSize = 16f
        setTextColor(android.graphics.Color.LTGRAY)
        text = tips.first()
    }

    private val handler = Handler(Looper.getMainLooper())
    private var index = 0

    init {
        addView(textView)
        startRefresh()
    }

    private fun startRefresh() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                index = (index + 1) % tips.size
                textView.text = tips[index]
                handler.postDelayed(this, 4000)
            }
        }, 4000)
    }
}
