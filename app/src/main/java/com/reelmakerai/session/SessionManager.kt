package com.reelmakerai.session

import com.reelmakerai.analytics.AnalyticsTracker
import com.reelmakerai.analytics.EventType

object SessionManager {

    fun startSession() {
        AnalyticsTracker.logEvent(EventType.SESSION_STARTED)
    }

    fun endSession() {
        AnalyticsTracker.logEvent(EventType.SESSION_ENDED)
    }
}
