package com.reelmakerai.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.reelmakerai.export.AIStudioActivity

object MediaLauncher {
    private const val VIDEO_PICK_REQUEST = 101

    fun launchVideoEditor(activity: Activity) {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "video/*"
        }
        activity.startActivityForResult(intent, VIDEO_PICK_REQUEST)
    }

    fun handleActivityResult(activity: Activity, requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == VIDEO_PICK_REQUEST && resultCode == Activity.RESULT_OK) {
            val selectedVideoUri = data?.data
            selectedVideoUri?.let {
                val intent = Intent(activity, AIStudioActivity::class.java).apply {
                    putExtra("video_uri", it.toString())
                }
                activity.startActivity(intent)
            }
        }
    }

    fun launchPhotoEditor(activity: Activity) {
        // Placeholder
    }

    fun launchCollageBuilder(activity: Activity) {
        // Placeholder
    }
}
