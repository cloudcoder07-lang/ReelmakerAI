package com.reelmakerai.ui

import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.ScaleAnimation

object AnimationUtils {

    fun pulse(): Animation {
        return ScaleAnimation(
            0.95f, 1.05f,
            0.95f, 1.05f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = 300
            repeatMode = Animation.REVERSE
            repeatCount = 1
            interpolator = AccelerateDecelerateInterpolator()
        }
    }
}
