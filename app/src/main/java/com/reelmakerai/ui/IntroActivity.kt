package com.reelmakerai.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainBoardActivity::class.java))
            finish()
        }, 2000)
    }
}
