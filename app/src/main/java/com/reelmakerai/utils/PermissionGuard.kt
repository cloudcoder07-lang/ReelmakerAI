package com.reelmakerai.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

/**
 * Executes [onGranted] only if the specified [permission] is granted.
 * Otherwise, runs [onDenied].
 */
inline fun withPermission(
    context: Context,
    permission: String,
    onGranted: () -> Unit,
    noinline onDenied: () -> Unit = {}
) {
    if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
        onGranted()
    } else {
        onDenied()
    }
}
