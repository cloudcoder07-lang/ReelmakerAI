package com.reelmakerai.export

import android.app.Activity
import android.content.Intent
import com.reelmakerai.ui.ExportResultActivity

object ExportTransitionManager {

    fun launchExportResult(activity: Activity, outputUri: android.net.Uri) {
        val intent = Intent(activity, ExportResultActivity::class.java)
        intent.putExtra("outputUri", outputUri)
        activity.startActivity(intent)
        activity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out)
    }
}
