package com.reelmakerai.filters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer

class SegmentationModel(context: Context) {

    private val interpreter: Interpreter

    init {
        val modelStream = context.assets.open("models/deeplabv3_257_mv_gpu.tflite")
        val modelBuffer = modelStream.readBytes().let { ByteBuffer.wrap(it) }
        interpreter = Interpreter(modelBuffer)

    }

    fun segmentForeground(input: Bitmap): Bitmap {
        val tensorImage = TensorImage.fromBitmap(input)
        val output = TensorBuffer.createFixedSize(intArrayOf(1, 257, 257, 1), tensorImage.dataType)
        interpreter.run(tensorImage.buffer, output.buffer)

        val mask = Bitmap.createBitmap(257, 257, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(mask)
        val paint = Paint()

        val pixels = output.buffer.rewind().let { ByteBuffer.wrap(output.buffer.array()) }
        for (y in 0 until 257) {
            for (x in 0 until 257) {
                val value = pixels.get().toInt() and 0xFF
                val alpha = if (value > 128) 255 else 0
                paint.alpha = alpha
                canvas.drawPoint(x.toFloat(), y.toFloat(), paint)
            }
        }

        return Bitmap.createScaledBitmap(mask, input.width, input.height, true)
    }
}
