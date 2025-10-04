package com.reelmakerai.monetization

import android.content.Context
import android.widget.Toast

object RewardTrigger {

    fun showAdAndUnlock(context: Context, packName: String) {
        Toast.makeText(context, "Simulating rewarded ad...", Toast.LENGTH_SHORT).show()
        PackUnlockManager.unlock(packName)
    }
}
