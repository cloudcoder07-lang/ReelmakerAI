package com.reelmakerai.ui

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import androidx.appcompat.app.AlertDialog

object NavigationManager {

    fun showCreateVideoOptions(activity: Activity) {
        val options = arrayOf("Record New Video", "Select from Gallery")
        AlertDialog.Builder(activity)
            .setTitle("Create Video")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> recordVideo(activity)
                    1 -> openGallery(activity)
                }
            }
            .show()
    }

    fun recordVideo(activity: Activity) {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        activity.startActivity(intent)
    }

    fun openGallery(activity: Activity) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "video/*"
        activity.startActivity(intent)
    }

    fun openSettings(activity: Activity) {
        AlertDialog.Builder(activity)
            .setMessage("Settings screen coming soon!")
            .setPositiveButton("OK", null)
            .show()
    }
}
