package com.reelmakerai.filters

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.nio.ByteBuffer
import java.nio.ByteOrder

class MoodFilterProcessor(context: Context) {

    private val interpreter: Interpreter

    init {
        val modelBytes = context.assets.open("models/MoodFilterModel.tflite").readBytes()
        val modelBuffer = ByteBuffer.allocateDirect(modelBytes.size).order(ByteOrder.nativeOrder())
        modelBuffer.put(modelBytes)
        modelBuffer.rewind()

        interpreter = Interpreter(modelBuffer)
    }

    fun predictMood(input: FloatArray): String {
        val inputBuffer = ByteBuffer.allocateDirect(4 * input.size).order(ByteOrder.nativeOrder())
        input.forEach { inputBuffer.putFloat(it) }
        inputBuffer.rewind()

        val output = Array(1) { FloatArray(3) } // e.g., [happy, sad, angry]
        interpreter.run(inputBuffer, output)

        val maxIndex = output[0].indices.maxByOrNull { output[0][it] } ?: 0
        return when (maxIndex) {
            0 -> "happy"
            1 -> "sad"
            else -> "angry"
        }
    }
}
