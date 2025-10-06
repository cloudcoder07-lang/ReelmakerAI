package com.reelmakerai.analytics

import android.util.Log
import com.reelmakerai.analytics.MetricType
import com.reelmakerai.analytics.PackAnalyticsTracker

object UnlockTelemetry {

    fun logUnlock(packName: String, method: String) {
        Log.d("UnlockTelemetry", "Unlocked $packName via $method")
        PackAnalyticsTracker.logPackUsage(packName, MetricType.UNLOCKED)
    }
}
