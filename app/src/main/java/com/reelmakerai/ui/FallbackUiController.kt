package com.reelmakerai.ui

import android.view.View
import android.widget.TextView

object FallbackUiController {

    fun showOfflineBanner(banner: View, messageView: TextView) {
        banner.visibility = View.VISIBLE
        messageView.text = "You're offline. Some features may be limited."
    }

    fun hideOfflineBanner(banner: View) {
        banner.visibility = View.GONE
    }
}
