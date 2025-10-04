package com.reelmakerai.analytics

import android.util.Log

object AnalyticsTracker {

    fun logEvent(event: EventType, details: String = "") {
        Log.d("Analytics", "${event.name}: $details")
        // TODO: Integrate Firebase or custom endpoint
    }
}
