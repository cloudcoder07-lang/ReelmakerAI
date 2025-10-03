package com.reelmakerai

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.ui.MainBoardActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Directly launch the new main board UI
        val intent = Intent(this, MainBoardActivity::class.java)
        startActivity(intent)

        // Optional: finish this activity so it doesn't stay in back stack
        finish()
    }
}
