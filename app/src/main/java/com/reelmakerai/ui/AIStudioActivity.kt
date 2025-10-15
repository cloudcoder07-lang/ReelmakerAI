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
import com.reelmakerai.model.*
import com.reelmakerai.tools.ToolDispatcher
import com.reelmakerai.ui.adapter.*
import com.reelmakerai.ui.editor.ResizeEditorActivity
import com.reelmakerai.ui.editor.CropEditorActivity
import com.reelmakerai.ui.editor.RotateEditorActivity
import com.reelmakerai.ui.editor.FlipEditorActivity
import com.reelmakerai.ui.editor.ZoomEditorActivity
import com.reelmakerai.ui.editor.BGFillEditorActivity


class AIStudioActivity : AppCompatActivity() {

    private lateinit var videoContainer: FrameLayout
    private lateinit var videoView: VideoView
    private lateinit var frameStrip: RecyclerView
    private lateinit var mainToolBar: RecyclerView
    private lateinit var subToolBar: RecyclerView
    private lateinit var btnPlayPause: ImageButton
    private lateinit var btnMaximize: ImageButton
    private lateinit var videoDuration: TextView
    private lateinit var overlayControls: View
    private lateinit var videoUri: Uri

    private val handler = Handler()
    private lateinit var updateRunnable: Runnable
    private val REQUEST_TOOL_EDIT = 999

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
        overlayControls = findViewById(R.id.overlayControls)

        val videoUriString = intent.getStringExtra("video_uri")
        if (!videoUriString.isNullOrEmpty()) {
            videoUri = Uri.parse(videoUriString)
            Log.d("AIStudio", "Received video URI: $videoUri")
            setupVideo()
            generateFrameStrip()
        } else {
            Log.e("AIStudio", "No video URI received")
            Toast.makeText(this, "No video selected", Toast.LENGTH_SHORT).show()
            finish()
        }

        setupToolbars()
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


    private fun setupVideo() {
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
    }

    private fun generateFrameStrip() {
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
                frameList.add(FrameItem(drawable))
            }
        }

        retriever.release()

        frameStrip.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        frameStrip.adapter = FrameStripAdapter(frameList)
    }

    private fun setupToolbars() {
        mainToolBar.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mainToolBar.adapter = MainToolAdapter(ToolType.values().toList()) { tool ->
            enterEditingMode(tool)
        }

        subToolBar.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun enterEditingMode(tool: ToolType) {
        mainToolBar.visibility = View.GONE
        subToolBar.visibility = View.VISIBLE

        applyToolProfile(tool.toProfile())

        val subItems = tool.getSubTools(this)
        subToolBar.adapter = SubToolAdapter(subItems) { item ->
            ToolDispatcher.dispatch(this, tool, item)
        }

        videoContainer.animate().translationY(72f).setDuration(300).start()
    }

    override fun onBackPressed() {
        if (subToolBar.visibility == View.VISIBLE || overlayControls.visibility == View.VISIBLE) {
            cleanupOverlay()
            resumeVideo()
        } else {
            super.onBackPressed()
        }
    }

    private fun cleanupOverlay() {
        overlayControls.visibility = View.GONE
        subToolBar.visibility = View.GONE
        mainToolBar.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction()
            .remove(supportFragmentManager.findFragmentById(R.id.overlayControls) ?: return)
            .commit()
        videoContainer.animate().translationY(0f).setDuration(300).start()
        videoContainer.requestLayout()
    }

    private fun resumeVideo() {
        videoView.start()
        btnPlayPause.setImageResource(R.drawable.ic_pause)
        handler.post(updateRunnable)
    }

    fun applyCanvasTransform(transform: CanvasTransform) {
        videoView.apply {
            rotation = transform.rotationDegrees.toFloat()
            scaleX = transform.scaleX
            scaleY = transform.scaleY
            // TODO: Add translation, crop, zoom logic as needed
        }
    }

    fun setEditingMode(enabled: Boolean) {
        videoView.isEnabled = enabled
        overlayControls.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    fun applyToolProfile(profile: ToolProfile) {
        val config = toolUiProfiles[profile] ?: return
        videoView.visibility = if (config.showVideo) View.VISIBLE else View.GONE
        overlayControls.visibility = if (config.showOverlay) View.VISIBLE else View.GONE
    }

    fun onToolSave(toolName: String) {
        Toast.makeText(this, "$toolName saved", Toast.LENGTH_SHORT).show()
        cleanupOverlay()
        resumeVideo()
    }

    fun onToolDiscard() {
        Toast.makeText(this, "Changes discarded", Toast.LENGTH_SHORT).show()
        cleanupOverlay()
        resumeVideo()
    }

    fun launchToolEditor(toolName: String) {
        videoView.pause()
        handler.removeCallbacks(updateRunnable)

        val editorClass: Class<*>? = when (toolName) {
            "Resize" -> ResizeEditorActivity::class.java
            "Crop" -> CropEditorActivity::class.java
            "Rotate" -> RotateEditorActivity::class.java
            "Flip" -> FlipEditorActivity::class.java
            "Zoom" -> ZoomEditorActivity::class.java
            "BGFill" -> BGFillEditorActivity::class.java
            else -> null
        }

        if (editorClass != null) {
            val intent = Intent(this, editorClass)
            intent.putExtra("tool_name", toolName)
            intent.putExtra("video_uri", videoUri.toString())
            startActivityForResult(intent, REQUEST_TOOL_EDIT)
        } else {
            Log.e("AIStudio", "Unknown tool editor: $toolName")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(updateRunnable)
        videoView.stopPlayback()
    }
}

