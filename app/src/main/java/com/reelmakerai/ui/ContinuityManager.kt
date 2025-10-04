package com.reelmakerai.ui

import android.content.Context

object ContinuityManager {

    fun restoreIfAvailable(context: Context): String? {
        return UiFlowRestorer.getLastFlow(context)
    }

    fun commitFlow(context: Context, flowId: String) {
        UiFlowRestorer.saveLastFlow(context, flowId)
    }
}
