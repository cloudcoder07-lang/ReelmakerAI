package com.reelmakerai.export

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream

object ExportUtils {

    fun getTempExportPath(context: Context): String {
        val dir = File(context.cacheDir, "exports")
        dir.mkdirs()
        return File(dir, "temp_export.mp4").absolutePath
    }

    fun saveToGallery(context: Context, file: File): Uri? {
        val values = ContentValues().apply {
            put(MediaStore.Video.Media.DISPLAY_NAME, file.name)
            put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
            put(MediaStore.Video.Media.RELATIVE_PATH, "DCIM/ReelMakerAI")
        }

        val resolver = context.contentResolver
        val uri = resolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values) ?: return null
        resolver.openOutputStream(uri)?.use { output ->
            file.inputStream().copyTo(output)
        }
        return uri
    }

    fun generateThumbnail(videoPath: String): Bitmap? {
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(videoPath)
        return retriever.getFrameAtTime(1_000_000, MediaMetadataRetriever.OPTION_CLOSEST)
    }
}
