package com.reelmakerai.ui

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation

object UiAnimator {

    fun fadeIn(view: View, duration: Long = 500) {
        val anim = AlphaAnimation(0f, 1f).apply {
            this.duration = duration
            fillAfter = true
        }
        view.startAnimation(anim)
    }

    fun fadeOut(view: View, duration: Long = 500) {
        val anim = AlphaAnimation(1f, 0f).apply {
            this.duration = duration
            fillAfter = true
        }
        view.startAnimation(anim)
    }
}
