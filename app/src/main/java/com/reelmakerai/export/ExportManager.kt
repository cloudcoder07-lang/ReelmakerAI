package com.reelmakerai.export

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

object ExportManager {

    fun exportReel(context: Context, data: ByteArray, filename: String): Uri? {
        val exportDir = File(context.getExternalFilesDir(Environment.DIRECTORY_MOVIES), "Reels")
        if (!exportDir.exists()) exportDir.mkdirs()

        val reelFile = File(exportDir, "$filename.mp4")
        try {
            FileOutputStream(reelFile).use { it.write(data) }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            reelFile
        )
    }
}
