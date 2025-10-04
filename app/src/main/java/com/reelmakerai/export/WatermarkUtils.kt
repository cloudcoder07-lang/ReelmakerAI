package com.reelmakerai.export

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint

object WatermarkUtils {

    fun applyWatermark(input: Bitmap): Bitmap {
        val output = input.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(output)
        val paint = Paint().apply {
            color = 0x88FFFFFF.toInt()
            textSize = 32f
            isAntiAlias = true
        }
        canvas.drawText("Made with ReelMakerAI", 20f, input.height - 40f, paint)
        return output
    }
}
