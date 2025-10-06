package com.reelmakerai.share

import android.content.Context
import android.content.Intent
import android.net.Uri

object ShareManager {
    fun shareVideo(context: Context, uri: Uri) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "video/mp4"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(intent, "Share your reel"))
    }
}
