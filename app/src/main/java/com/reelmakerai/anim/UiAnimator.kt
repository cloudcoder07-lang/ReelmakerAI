package com.reelmakerai.anim

import android.view.View
import android.view.animation.AnimationUtils
import com.reelmakerai.R

object UiAnimator {
    fun fadeIn(view: View) {
        val animation = AnimationUtils.loadAnimation(view.context, R.anim.fade_in)
        view.startAnimation(animation)
        view.visibility = View.VISIBLE
    }
}
