package com.reelmakerai.overlay

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface

object OverlayManager {

    fun renderOverlays(canvas: Canvas, overlays: List<OverlayItem>) {
        overlays.forEach { item ->
            when (item) {
                is OverlayItem.Sticker -> {
                    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
                    canvas.save()
                    canvas.concat(item.transform)
                    canvas.drawBitmap(item.bitmap, null, item.bounds, paint)
                    canvas.restore()
                }
                is OverlayItem.Text -> {
                    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                        textSize = item.fontSize
                        typeface = Typeface.DEFAULT_BOLD
                        color = android.graphics.Color.WHITE
                    }
                    canvas.save()
                    canvas.concat(item.transform)
                    canvas.drawText(item.text, item.bounds.left, item.bounds.bottom, paint)
                    canvas.restore()
                }
            }
        }
    }
}
