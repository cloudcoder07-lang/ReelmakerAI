package com.reelmakerai.audio

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

object FxThumbnailGenerator {

    fun generateThumbnail(effectType: EffectType): Bitmap {
        val bmp = Bitmap.createBitmap(128, 128, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)
        val paint = Paint().apply {
            color = when (effectType) {
                EffectType.ROBOT -> Color.RED
                EffectType.ECHO -> Color.BLUE
                EffectType.CHIPMUNK -> Color.GREEN
                EffectType.DEEP -> Color.MAGENTA
            }
            style = Paint.Style.FILL
        }
        canvas.drawCircle(64f, 64f, 48f, paint)
        return bmp
    }
}
