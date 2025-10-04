package com.reelmakerai.ui

import android.view.View

object FinalUxPolish {

    fun elevate(view: View) {
        view.elevation = 12f
        view.alpha = 0.95f
    }

    fun dim(view: View) {
        view.alpha = 0.6f
    }
}
