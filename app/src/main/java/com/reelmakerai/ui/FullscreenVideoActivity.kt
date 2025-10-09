package com.reelmakerai.ui

import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R

class FullscreenVideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_video)

        val videoView = findViewById<VideoView>(R.id.fullscreenVideoView)
        val playPause = findViewById<ImageButton>(R.id.btnFullscreenPlayPause)
        val btnMinimize = findViewById<ImageButton>(R.id.btnMinimize)

        val uriString = intent.getStringExtra("video_uri")
        val position = intent.getIntExtra("video_position", 0)
        val videoUri = Uri.parse(uriString)

        videoView.setVideoURI(videoUri)
        videoView.setOnPreparedListener {
            videoView.seekTo(position)
            playPause.setImageResource(R.drawable.ic_play)
        }

        playPause.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
                playPause.setImageResource(R.drawable.ic_play)
            } else {
                videoView.start()
                playPause.setImageResource(R.drawable.ic_pause)

                videoView.setOnCompletionListener {
                    playPause.setImageResource(R.drawable.ic_play)
                }
            }
        }

        btnMinimize.setOnClickListener {
            finish()
        }
    }
}
