package com.reelmakerai.ui

import android.content.Context
import android.content.SharedPreferences

object UiFlowRestorer {

    private const val PREFS = "ui_memory"
    private const val LAST_FLOW = "last_flow"

    fun saveLastFlow(context: Context, flowId: String) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putString(LAST_FLOW, flowId)
            .apply()
    }

    fun getLastFlow(context: Context): String? {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getString(LAST_FLOW, null)
    }
}
