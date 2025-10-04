package com.reelmakerai.analytics

import android.os.Handler
import android.os.Looper

class EngagementMonitor(
    private val timeoutMillis: Long = 30000,
    private val onIdle: () -> Unit
) {
    private val handler = Handler(Looper.getMainLooper())
    private val runnable = Runnable { onIdle() }

    fun start() {
        stop()
        handler.postDelayed(runnable, timeoutMillis)
    }

    fun stop() {
        handler.removeCallbacks(runnable)
    }

    fun reset() {
        start()
    }
}
