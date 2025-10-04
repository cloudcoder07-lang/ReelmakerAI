package com.reelmakerai.analytics

import android.util.Log

object PackAnalyticsTracker {

    fun logPackUsage(packName: String, metric: MetricType) {
        Log.d("PackAnalytics", "$packName → ${metric.name}")
        // TODO: Push to analytics backend
    }
}
