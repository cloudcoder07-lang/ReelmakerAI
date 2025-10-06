package com.reelmakerai.referral

import android.content.Context
import android.content.Intent

object ReferralEngine {
    fun inviteFriend(context: Context) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Join me on ReelMakerAI!")
        }
        context.startActivity(Intent.createChooser(intent, "Invite via"))
    }
}
