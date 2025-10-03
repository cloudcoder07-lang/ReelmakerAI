package com.reelmakerai.voice

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class VoiceTriggerEngine(context: Context) {

    private val interpreter: Interpreter
    private val labels: List<String>

    init {
        val model = loadModelFile(context, "voice_fx_model.tflite")
        interpreter = Interpreter(model)
        labels = loadLabels(context, "voice_fx_labels.txt")
    }

    private fun loadModelFile(context: Context, modelName: String): MappedByteBuffer {
        val fileDescriptor = context.assets.openFd(modelName)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, fileDescriptor.startOffset, fileDescriptor.declaredLength)
    }

    private fun loadLabels(context: Context, fileName: String): List<String> {
        return context.assets.open(fileName).bufferedReader().readLines()
    }

    fun detectCommand(audioBuffer: FloatArray): String? {
        val input = arrayOf(audioBuffer)
        val output = Array(1) { FloatArray(labels.size) }
        interpreter.run(input, output)

        val maxIndex = output[0].indices.maxByOrNull { output[0][it] } ?: return null
        val confidence = output[0][maxIndex]
        return if (confidence > 0.8f) labels[maxIndex] else null
    }
}
