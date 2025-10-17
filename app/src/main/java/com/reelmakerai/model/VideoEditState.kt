package com.reelmakerai.model

import android.net.Uri

data class VideoEditState(
    val videoUri: Uri,
    val aspectRatio: Float,
    val toolUsed: String,
    val timestamp: Long
)

