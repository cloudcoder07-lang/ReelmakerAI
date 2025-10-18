package com.reelmakerai.ui.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.reelmakerai.model.AspectRatio

class CropOverlayView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint().apply {
        color = Color.WHITE
        strokeWidth = 4f
        style = Paint.Style.STROKE
    }

    private var aspectRatio: Float = 0f
    private var cropRect = RectF()

    fun setAspectRatio(ratio: AspectRatio) {
        aspectRatio = ratio.value
        invalidate()
    }

    fun getCropRect(): RectF = cropRect

    fun getResolutionLabel(): String {
        val width = cropRect.width().toInt()
        val height = cropRect.height().toInt()
        return "${width}x${height}"
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w = width.toFloat()
        val h = height.toFloat()

        val targetW: Float
        val targetH: Float

        when {
            aspectRatio == 0f -> {
                cropRect.set(0f, 0f, w, h)
            }
            aspectRatio == -1f -> {
                cropRect.set(0f, 0f, w, h) // Original — fallback
            }
            else -> {
                if (w / h > aspectRatio) {
                    targetH = h
                    targetW = h * aspectRatio
                } else {
                    targetW = w
                    targetH = w / aspectRatio
                }
                val left = (w - targetW) / 2
                val top = (h - targetH) / 2
                cropRect.set(left, top, left + targetW, top + targetH)
            }
        }

        canvas.drawRect(cropRect, paint)

        val label = getResolutionLabel()
        paint.textSize = 36f
        paint.style = Paint.Style.FILL
        canvas.drawText(label, cropRect.centerX() - 80, cropRect.centerY(), paint)
        paint.style = Paint.Style.STROKE
    }
}
