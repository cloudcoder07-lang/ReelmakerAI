package com.reelmakerai.anim

import android.app.Activity
import android.content.Intent
import android.view.animation.AnimationUtils
import com.reelmakerai.R

object TransitionManager {
    fun fadeTo(activity: Activity, target: Class<*>) {
        val intent = Intent(activity, target)
        activity.startActivity(intent)
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}
