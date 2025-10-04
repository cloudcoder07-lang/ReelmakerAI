package com.reelmakerai.social

import android.content.Context
import android.content.Intent
import android.net.Uri

object ShareManager {

    fun shareVideo(context: Context, videoUri: Uri) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "video/mp4"
            putExtra(Intent.EXTRA_STREAM, videoUri)
        }
        context.startActivity(Intent.createChooser(intent, "Share your reel"))
    }
}
