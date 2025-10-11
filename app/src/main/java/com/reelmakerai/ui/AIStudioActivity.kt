package com.reelmakerai.ui

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reelmakerai.R
import com.reelmakerai.model.FrameItem
import com.reelmakerai.model.ToolType
import com.reelmakerai.ui.adapter.FrameStripAdapter
import com.reelmakerai.ui.adapter.MainToolAdapter
import com.reelmakerai.ui.adapter.SubToolAdapter
import com.reelmakerai.tools.ToolDispatcher

class AIStudioActivity : AppCompatActivity() {

    private lateinit var videoContainer: FrameLayout
    private lateinit var videoView: VideoView
    private lateinit var frameStrip: RecyclerView
    private lateinit var mainToolBar: RecyclerView
    private lateinit var subToolBar: RecyclerView
    private lateinit var btnPlayPause: ImageButton
    private lateinit var btnMaximize: ImageButton
    private lateinit var videoDuration: TextView
    private lateinit var videoUri: Uri

    private val handler = Handler()
    private lateinit var updateRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_studio)

        videoContainer = findViewById(R.id.videoContainer)
        videoView = findViewById(R.id.videoPreview)
        frameStrip = findViewById(R.id.frameStrip)
        mainToolBar = findViewById(R.id.mainToolBar)
        subToolBar = findViewById(R.id.subToolBar)
        btnPlayPause = findViewById(R.id.btnPlayPause)
        btnMaximize = findViewById(R.id.btnMaximize)
        videoDuration = findViewById(R.id.videoDuration)

        val videoUriString = intent.getStringExtra("video_uri")
        if (!videoUriString.isNullOrEmpty()) {
            videoUri = Uri.parse(videoUriString)
            Log.d("AIStudio", "Received video URI: $videoUri")
            videoView.setVideoURI(videoUri)

            videoView.setOnPreparedListener { mp ->
                mp.isLooping = false
                videoView.seekTo(0)
                btnPlayPause.setImageResource(R.drawable.ic_play)
                updateDuration(0, mp.duration)
            }

            updateRunnable = object : Runnable {
                override fun run() {
                    if (videoView.isPlaying) {
                        updateDuration(videoView.currentPosition, videoView.duration)
                        handler.postDelayed(this, 1000)
                    }
                }
            }

            btnPlayPause.setOnClickListener {
                if (videoView.isPlaying) {
                    videoView.pause()
                    btnPlayPause.setImageResource(R.drawable.ic_play)
                    handler.removeCallbacks(updateRunnable)
                    updateDuration(videoView.currentPosition, videoView.duration)
                } else {
                    videoView.start()
                    btnPlayPause.setImageResource(R.drawable.ic_pause)
                    handler.post(updateRunnable)

                    videoView.setOnCompletionListener {
                        btnPlayPause.setImageResource(R.drawable.ic_play)
                        handler.removeCallbacks(updateRunnable)
                        updateDuration(videoView.duration, videoView.duration)
                    }
                }
            }

            btnMaximize.setOnClickListener {
                val intent = Intent(this, FullscreenVideoActivity::class.java)
                intent.putExtra("video_uri", videoUri.toString())
                intent.putExtra("video_position", videoView.currentPosition)
                startActivity(intent)
            }

            // ✅ Extract frames from video
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(this, videoUri)

            val durationMs = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLongOrNull() ?: 0L
            val frameCount = 10
            val interval = durationMs / frameCount
            val frameList = mutableListOf<FrameItem>()

            for (i in 0 until frameCount) {
                val timeUs = i * interval * 1000
                val bitmap = retriever.getFrameAtTime(timeUs, MediaMetadataRetriever.OPTION_CLOSEST)
                if (bitmap != null) {
                    val drawable = BitmapDrawable(resources, bitmap)
                    frameList.add(FrameItem(drawable)) // ✅ Only one argument
                }
            }

            retriever.release()

            frameStrip.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            frameStrip.adapter = FrameStripAdapter(frameList)
        } else {
            Log.e("AIStudio", "No video URI received")
        }

        mainToolBar.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val mainAdapter = MainToolAdapter(ToolType.values().toList()) { tool ->
            enterEditingMode(tool)
        }
        mainToolBar.adapter = mainAdapter

        subToolBar.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun enterEditingMode(tool: ToolType) {
        mainToolBar.visibility = View.GONE
        subToolBar.visibility = View.VISIBLE
        //videoContainer.animate().translationYBy(-72f).setDuration(300).start()

        val subItems = tool.getSubTools(this)  // ✅ Pass context here
        val subAdapter = SubToolAdapter(subItems) { item ->
            ToolDispatcher.dispatch(tool, item)
        }
        subToolBar.adapter = subAdapter
    }

    override fun onBackPressed() {
        if (subToolBar.visibility == View.VISIBLE) {
            subToolBar.visibility = View.GONE
            mainToolBar.visibility = View.VISIBLE
            videoContainer.animate().translationYBy(72f).setDuration(300).start()
        } else {
            super.onBackPressed()
        }
    }

    private fun updateDuration(currentMs: Int, totalMs: Int) {
        val current = formatTime(currentMs)
        val total = formatTime(totalMs)
        videoDuration.text = "$current / $total"
    }

    private fun formatTime(ms: Int): String {
        val seconds = (ms / 1000) % 60
        val minutes = (ms / 1000) / 60
        return String.format("%02d:%02d", minutes, seconds)
    }
}
