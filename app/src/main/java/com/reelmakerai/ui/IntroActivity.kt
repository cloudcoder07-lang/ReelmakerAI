package com.reelmakerai.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R
import com.reelmakerai.anim.UiAnimator
import com.reelmakerai.anim.TransitionManager

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val logo = findViewById<View>(R.id.logo)
        UiAnimator.fadeIn(logo)

        Handler(mainLooper).postDelayed({
            TransitionManager.fadeTo(this, MainBoardActivity::class.java)
            finish()
        }, 2000)
    }
}
