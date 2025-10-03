package com.reelmakerai.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R

class MainBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_board)

        findViewById<android.view.View>(R.id.btnVideo).setOnClickListener {
            Toast.makeText(this, "Create Video clicked", Toast.LENGTH_SHORT).show()
            // Launch video creation flow
        }

        findViewById<android.view.View>(R.id.btnPhoto).setOnClickListener {
            Toast.makeText(this, "Create Photo clicked", Toast.LENGTH_SHORT).show()
            // Launch photo editor
        }

        findViewById<android.view.View>(R.id.btnCollage).setOnClickListener {
            Toast.makeText(this, "Create Collage clicked", Toast.LENGTH_SHORT).show()
            // Launch collage editor
        }
    }
}
