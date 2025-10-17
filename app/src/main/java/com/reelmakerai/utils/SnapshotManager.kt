package com.reelmakerai.util

import android.content.Context
import android.net.Uri
import android.util.Log
import com.reelmakerai.session.EditSession
import java.io.File

object SnapshotManager {

    fun createSnapshotIfNeeded(context: Context, originalUri: Uri) {
        if (EditSession.snapshotPath == null) {
            val snapshotFile = File(context.cacheDir, "snapshot.mp4")
            context.contentResolver.openInputStream(originalUri)?.use { input ->
                snapshotFile.outputStream().use { output -> input.copyTo(output) }
            }
            EditSession.snapshotPath = snapshotFile.absolutePath
        }
    }

    fun commitTempEdit(): Boolean {
        val temp = EditSession.tempEditPath ?: return false
        val snapshot = EditSession.snapshotPath ?: return false
        File(temp).copyTo(File(snapshot), overwrite = true)
        File(temp).delete()
        EditSession.tempEditPath = null
        return true
    }

    fun discardTempEdit() {
        EditSession.tempEditPath?.let {
            File(it).delete()
            EditSession.tempEditPath = null
        }
    }
}
