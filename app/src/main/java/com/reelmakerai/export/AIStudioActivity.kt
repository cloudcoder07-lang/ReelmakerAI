package com.reelmakerai.export

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reelmakerai.R
import com.reelmakerai.ui.SubscriptionManager
import com.reelmakerai.ui.ToolbarAdapter
import com.reelmakerai.ui.FramePreviewAdapter
import com.reelmakerai.ui.ToolbarItem

class AIStudioActivity : AppCompatActivity() {

    private lateinit var videoUri: Uri
    private lateinit var videoView: VideoView
    //private lateinit var watermarkText: TextView
    private lateinit var framePreview: RecyclerView
    private lateinit var toolbar: RecyclerView
    private lateinit var voiceFxSwitch: Switch
    private lateinit var exportButton: Button
    private lateinit var addVideoButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_studio)

        // Get video URI
        videoUri = intent.getStringExtra("video_uri")?.let { Uri.parse(it) }
            ?: run {
                Toast.makeText(this, "Missing video URI", Toast.LENGTH_SHORT).show()
                finish()
                return
            }

        // Initialize views
        videoView = findViewById(R.id.videoView)
        //watermarkText = findViewById(R.id.watermarkText)
        framePreview = findViewById(R.id.framePreview)
        toolbar = findViewById(R.id.toolbar)
        voiceFxSwitch = findViewById(R.id.voiceFxSwitch)
        exportButton = findViewById(R.id.btnExport)
        addVideoButton = findViewById(R.id.btnAddVideo)

        // Setup video preview
        videoView.setVideoURI(videoUri)
        videoView.setOnPreparedListener { it.isLooping = true }
        videoView.start()

        // Watermark visibility
        //val isPremiumUser = SubscriptionManager.isPremium(this)
        //watermarkText.visibility = if (isPremiumUser) View.GONE else View.VISIBLE

        // Toolbar setup
        toolbar.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        toolbar.adapter = ToolbarAdapter(getToolbarItems())

        // Frame preview setup (placeholder)
        framePreview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        framePreview.adapter = FramePreviewAdapter(videoUri)

        // Voice FX toggle
        voiceFxSwitch.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "Voice FX On" else "Voice FX Off", Toast.LENGTH_SHORT).show()
        }

        // Export button
        exportButton.setOnClickListener {
            Toast.makeText(this, "Exporting Reel...", Toast.LENGTH_SHORT).show()
            // TODO: Trigger export pipeline
        }

        // Add video button
        addVideoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK).apply { type = "video/*" }
            startActivityForResult(intent, 102)
        }
    }

    private fun getToolbarItems(): List<ToolbarItem> {
        return listOf(
            ToolbarItem(R.drawable.ic_canvas, "Canvas"),
            ToolbarItem(R.drawable.ic_audio, "Audio"),
            ToolbarItem(R.drawable.ic_sticker, "Sticker"),
            ToolbarItem(R.drawable.ic_text, "Text"),
            ToolbarItem(R.drawable.ic_effect, "Effect"),
            ToolbarItem(R.drawable.ic_filter, "Filter")
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 102 && resultCode == RESULT_OK) {
            val newUri = data?.data ?: return
            Toast.makeText(this, "New video added", Toast.LENGTH_SHORT).show()
            // TODO: Merge or queue new video
        }
    }
}
