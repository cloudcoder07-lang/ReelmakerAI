package com.reelmakerai.ui

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R

class ExportResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export_result)

        val videoView = findViewById<VideoView>(R.id.finalVideoView)
        val outputUri = intent.getParcelableExtra<Uri>("outputUri")
        videoView.setVideoURI(outputUri)
        videoView.start()
    }
}
