package com.reelmakerai.audio

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

object FxThumbnailGenerator {

    fun generate(effect: EffectType): Bitmap {
        val bmp = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)
        val paint = Paint().apply {
            color = when (effect) {
                EffectType.None -> Color.GRAY
                EffectType.Robot -> Color.RED
                EffectType.Echo -> Color.BLUE
                EffectType.Chipmunk -> Color.YELLOW
                EffectType.Deep -> Color.GREEN
                EffectType.Alien -> Color.MAGENTA
                EffectType.Cave -> Color.CYAN
                EffectType.Fast -> Color.LTGRAY
                EffectType.Slow -> Color.DKGRAY
            }
        }
        canvas.drawCircle(100f, 100f, 80f, paint)
        return bmp
    }
}
