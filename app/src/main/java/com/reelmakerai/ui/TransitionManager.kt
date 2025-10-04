package com.reelmakerai.ui

import android.app.Activity
import android.content.Intent
import android.view.animation.AnimationUtils
import com.reelmakerai.R

object TransitionManager {

    fun fadeTo(activity: Activity, target: Class<out Activity>) {
        val intent = Intent(activity, target)
        activity.startActivity(intent)
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}
