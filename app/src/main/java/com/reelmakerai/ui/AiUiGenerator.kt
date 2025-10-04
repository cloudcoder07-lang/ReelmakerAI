package com.reelmakerai.ui

import android.content.Context
import android.view.View
import android.widget.TextView

object AiUiGenerator {

    fun generateWelcomeView(context: Context): View {
        return TextView(context).apply {
            text = "Welcome back to ReelMakerAI!"
            textSize = 18f
            setPadding(24, 24, 24, 24)
        }
    }
}
