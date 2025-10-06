package com.reelmakerai.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R
import com.reelmakerai.anim.UiAnimator

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val logo = findViewById<View>(R.id.logo)
        val branding = findViewById<View>(R.id.brandingText)

        UiAnimator.fadeIn(logo)
        UiAnimator.fadeIn(branding)

        Log.d("IntroActivity", "Splash screen loaded")

        Handler(mainLooper).postDelayed({
            try {
                val intent = Intent(this, MainBoardActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                }
                startActivity(intent)
                Log.d("IntroActivity", "MainBoardActivity launched")
            } catch (e: Exception) {
                Log.e("IntroActivity", "Failed to launch MainBoardActivity", e)
            } finally {
                finish()
            }
        }, 2000)
    }
}
