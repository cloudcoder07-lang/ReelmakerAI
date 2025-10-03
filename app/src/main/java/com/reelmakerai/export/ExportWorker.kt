package com.reelmakerai.export

import android.os.Handler
import android.os.Looper
import java.io.File

object ExportWorker {

    fun run(task: ExportTask, callback: ExportCallback) {
        val handler = Handler(Looper.getMainLooper())
        var progress = 0

        handler.postDelayed(object : Runnable {
            override fun run() {
                progress += 10
                callback.onProgress(progress)

                if (progress >= 100) {
                    callback.onComplete(File(task.outputPath))
                } else {
                    handler.postDelayed(this, 300)
                }
            }
        }, 300)
    }
}
