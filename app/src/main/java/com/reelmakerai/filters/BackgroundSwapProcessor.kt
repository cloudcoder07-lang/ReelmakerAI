package com.reelmakerai.filters

import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Canvas
import android.graphics.Bitmap
import android.content.Context
import org.tensorflow.lite.support.image.TensorImage

object BackgroundSwapProcessor {

    fun swapBackground(original: Bitmap, background: Bitmap): Bitmap {
        // TODO: Use TensorFlow Lite segmentation model to mask foreground
        // Composite foreground over new background
        return original // placeholder
    }
    fun compositeForeground(context: Context, original: Bitmap, background: Bitmap): Bitmap {
        val segmenter = SegmentationModel(context)
        val mask = segmenter.segmentForeground(original)

        val result = Bitmap.createBitmap(original.width, original.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)
        canvas.drawBitmap(background, 0f, 0f, null)
        canvas.drawBitmap(original, 0f, 0f, Paint().apply { xfermode = android.graphics.PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN) })
        canvas.drawBitmap(mask, 0f, 0f, null)
        return result
    }

}
