package com.reelmakerai.util

import android.net.Uri
import android.widget.VideoView
import com.reelmakerai.session.EditSession
import java.io.File

object VideoPreviewUtils {
    fun loadSnapshotIntoPreview(videoView: VideoView) {
        EditSession.snapshotPath?.let {
            val uri = Uri.fromFile(File(it))
            videoView.setVideoURI(uri)
            videoView.start()
        }
    }
}
