package com.reelmakerai.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R

class AIStudioActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var voiceFxStatus: TextView
    private lateinit var toolBar: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_studio)

        videoView = findViewById(R.id.videoView)
        voiceFxStatus = findViewById(R.id.voiceFxStatus)
        toolBar = findViewById(R.id.toolBar)

        val videoUri = intent.getStringExtra("video_uri")?.let { Uri.parse(it) }

        if (videoUri == null) {
            Toast.makeText(this, "No video received", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        Log.d("AIStudio", "Received video URI: $videoUri")

        videoView.setVideoURI(videoUri)
        videoView.setMediaController(MediaController(this))
        videoView.setOnPreparedListener { videoView.start() }

        voiceFxStatus.setOnClickListener {
            val current = voiceFxStatus.text.toString()
            val newState = if (current.contains("OFF")) "VOICE FX: ON" else "VOICE FX: OFF"
            voiceFxStatus.text = newState
        }

        findViewById<Button>(R.id.btnSwapBackground).setOnClickListener {
            Toast.makeText(this, "Swap Background triggered", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnExport).setOnClickListener {
            Toast.makeText(this, "Exporting reel...", Toast.LENGTH_SHORT).show()
        }

        loadToolBar()
    }

    private fun loadToolBar() {
        val tools = listOf(
            Tool("Text", R.drawable.ic_text),
            Tool("Audio", R.drawable.ic_audio),
            Tool("Sticker", R.drawable.ic_sticker),
            Tool("Effect", R.drawable.ic_effect),
            Tool("Filter", R.drawable.ic_filter),
            Tool("PIP", R.drawable.ic_pip)
        )

        tools.forEach { tool ->
            val toolView = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                gravity = android.view.Gravity.CENTER
                setPadding(8, 8, 8, 8)

                val icon = ImageView(this@AIStudioActivity).apply {
                    setImageResource(tool.iconRes)
                    setColorFilter(android.graphics.Color.WHITE)
                    layoutParams = LinearLayout.LayoutParams(64, 64)
                }

                val label = TextView(this@AIStudioActivity).apply {
                    text = tool.name
                    setTextColor(android.graphics.Color.LTGRAY)
                    textSize = 10f
                    gravity = android.view.Gravity.CENTER
                }

                addView(icon)
                addView(label)
            }

            toolBar.addView(toolView)
        }
    }

    data class Tool(val name: String, val iconRes: Int)
}
