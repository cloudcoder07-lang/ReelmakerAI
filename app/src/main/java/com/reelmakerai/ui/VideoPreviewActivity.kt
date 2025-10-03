package com.reelmakerai.ui

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R

class VideoPreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_preview)

        val videoUri = intent.getStringExtra("video_uri")?.let { Uri.parse(it) }

        if (videoUri == null) {
            Toast.makeText(this, "No video received", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val videoView = findViewById<VideoView>(R.id.videoView)
        videoView.setVideoURI(videoUri)
        videoView.setMediaController(MediaController(this))
        videoView.setOnPreparedListener { videoView.start() }
    }
}
