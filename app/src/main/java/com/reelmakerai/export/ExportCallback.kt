package com.reelmakerai.export

import java.io.File

interface ExportCallback {
    fun onProgress(percent: Int)
    fun onComplete(outputFile: File)
    fun onError(message: String)
}
