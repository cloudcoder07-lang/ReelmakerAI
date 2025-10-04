package com.reelmakerai.analytics

object UnlockTelemetry {

    fun logUnlock(packName: String, method: String) {
        Log.d("UnlockTelemetry", "Unlocked $packName via $method")
        PackAnalyticsTracker.logPackUsage(packName, MetricType.UNLOCKED)
    }
}
