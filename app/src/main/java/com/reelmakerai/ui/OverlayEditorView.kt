package com.reelmakerai.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.reelmakerai.overlay.OverlayItem
import com.reelmakerai.overlay.OverlayManager

class OverlayEditorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    var overlays: List<OverlayItem> = emptyList()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        OverlayManager.renderOverlays(canvas, overlays)
    }
}
