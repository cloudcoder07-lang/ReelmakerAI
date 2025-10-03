package com.reelmakerai.overlay

import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.RectF

sealed class OverlayItem {
    data class Sticker(val bitmap: Bitmap, val bounds: RectF, val transform: Matrix) : OverlayItem()
    data class Text(val text: String, val bounds: RectF, val transform: Matrix, val fontSize: Float) : OverlayItem()
}
