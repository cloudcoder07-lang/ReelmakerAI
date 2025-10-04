package com.reelmakerai.ui

import android.os.Handler
import android.os.Looper

class IdleRefreshTrigger(
    private val timeoutMillis: Long = 60000,
    private val onRefresh: () -> Unit
) {
    private val handler = Handler(Looper.getMainLooper())
    private val runnable = Runnable { onRefresh() }

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
