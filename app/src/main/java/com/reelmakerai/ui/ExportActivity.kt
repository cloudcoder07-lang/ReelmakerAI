package com.reelmakerai.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R
import com.reelmakerai.social.ShareManager

class ExportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export)

        val shareButton = findViewById<Button>(R.id.shareButton)
        val doneButton = findViewById<Button>(R.id.doneButton)

        shareButton.setOnClickListener {
            // TODO: Implement share logic
            ShareManager.shareVideo(this, null) // Replace null with actual Uri
        }

        doneButton.setOnClickListener {
            finish()
        }
    }
}
