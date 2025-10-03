package com.reelmakerai.export

import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import java.io.File

object LutExportController {

    fun export(task: ExportTask, callback: ExportCallback) {
        ExportWorker.run(task, callback)
    }
}
