package com.reelmakerai.store

import android.content.Context
import android.content.Intent
import android.net.Uri

object PlayStoreUtils {

    fun openAppPage(context: Context) {
        val uri = Uri.parse("market://details?id=${context.packageName}")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun promptReview(context: Context) {
        // TODO: Integrate Google Play In-App Review API
        openAppPage(context)
    }
}
