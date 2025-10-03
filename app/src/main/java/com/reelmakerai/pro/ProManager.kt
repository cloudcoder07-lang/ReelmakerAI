package com.reelmakerai.pro

import android.content.Context
import android.content.SharedPreferences

object ProManager {

    private const val PREFS_NAME = "pro_prefs"
    private const val KEY_IS_PRO = "is_pro"

    fun isPro(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_IS_PRO, false)
    }

    fun unlockPro(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_IS_PRO, true).apply()
    }

    fun enforceExportLimit(context: Context): Boolean {
        if (isPro(context)) return true
        // TODO: Track export count and limit free users
        return false
    }
}
