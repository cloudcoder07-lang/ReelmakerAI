package com.reelmakerai.social

import android.content.Context
import android.content.Intent

object ReferralEngine {

    fun inviteFriend(context: Context) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Join me on ReelMakerAI: https://play.google.com/store/apps/details?id=${context.packageName}")
        }
        context.startActivity(Intent.createChooser(intent, "Invite a friend"))
    }
}
