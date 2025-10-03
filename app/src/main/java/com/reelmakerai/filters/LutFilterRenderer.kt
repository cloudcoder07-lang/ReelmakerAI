package com.reelmakerai.filters

import android.opengl.GLES20

object LutFilterRenderer {

    fun applyLutToPixel(r: Float, g: Float, b: Float, lut: List<FloatArray>): FloatArray {
        val index = ((r + g + b) / 3 * (lut.size - 1)).toInt().coerceIn(0, lut.size - 1)
        return lut[index]
    }

    fun applyLutToFrame(frame: Array<Array<FloatArray>>, lut: List<FloatArray>): Array<Array<FloatArray>> {
        return Array(frame.size) { y ->
            Array(frame[0].size) { x ->
                val pixel = frame[y][x]
                applyLutToPixel(pixel[0], pixel[1], pixel[2], lut)
            }
        }
    }
}
