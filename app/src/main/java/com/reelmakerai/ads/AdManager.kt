package com.reelmakerai.ads

import android.content.Context
import android.widget.Toast
import com.reelmakerai.monetization.PackUnlockManager
import com.reelmakerai.analytics.UnlockTelemetry

object AdManager {

    fun showRewardedAd(context: Context, packName: String) {
        // Simulate ad flow for now
        Toast.makeText(context, "Simulating rewarded ad...", Toast.LENGTH_SHORT).show()

        // Unlock the pack
        PackUnlockManager.unlock(packName)

        // Log unlock telemetry
        UnlockTelemetry.logUnlock(packName, "rewarded_ad")
    }
}
