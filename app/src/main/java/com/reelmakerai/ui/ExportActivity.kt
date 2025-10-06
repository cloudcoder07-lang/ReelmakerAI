package com.reelmakerai.ui

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R
import com.reelmakerai.social.ShareManager

class ExportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export)

        val shareButton = findViewById<Button>(R.id.shareButton)
        val doneButton = findViewById<Button>(R.id.doneButton)

        val outputUri = intent.getParcelableExtra<Uri>("outputUri")

        shareButton.setOnClickListener {
            if (outputUri != null) {
                ShareManager.shareVideo(this, outputUri)
            } else {
                Toast.makeText(this, "Export file not found", Toast.LENGTH_SHORT).show()
            }
        }

        doneButton.setOnClickListener {
            finish()
        }
    }
}
