package com.reelmakerai.ui.editor

import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R

class BGFillEditorActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var toolControlContainer: LinearLayout
    private lateinit var btnExport: ImageButton
    private lateinit var btnClose: ImageButton
    private lateinit var videoUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tool_editor)

        videoView = findViewById(R.id.videoPreview)
        toolControlContainer = findViewById(R.id.toolControlContainer)
        btnExport = findViewById(R.id.btnExport)
        btnClose = findViewById(R.id.btnClose)

        videoUri = Uri.parse(intent.getStringExtra("video_uri"))
        videoView.setVideoURI(videoUri)
        videoView.setOnPreparedListener { mp ->
            mp.isLooping = true
            videoView.start()
        }

        btnExport.setOnClickListener {
            Toast.makeText(this, "Export BGFill", Toast.LENGTH_SHORT).show()
        }

        btnClose.setOnClickListener {
            finish()
        }

        injectBGFillControls()
    }

    private fun injectBGFillControls() {
        val bgFillControls = layoutInflater.inflate(R.layout.view_bgfill_controls, toolControlContainer, false)
        toolControlContainer.addView(bgFillControls)
    }
}
