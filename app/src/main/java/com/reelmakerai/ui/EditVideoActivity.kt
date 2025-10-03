package com.reelmakerai.ui

import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R

class EditVideoActivity : AppCompatActivity() {

    private lateinit var videoUri: Uri
    private lateinit var videoView: VideoView
    private lateinit var lutContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_video)

        videoUri = intent.getStringExtra("video_uri")?.let { Uri.parse(it) } ?: return

        videoView = findViewById(R.id.videoView)
        videoView.setVideoURI(videoUri)
        videoView.setOnPreparedListener { videoView.start() }

        lutContainer = findViewById(R.id.lutContainer)
        loadLUTOptions()

        findViewById<Button>(R.id.btnVoiceFX).setOnClickListener {
            // TODO: Apply voice FX
        }

        findViewById<Button>(R.id.btnBackgroundSwap).setOnClickListener {
            // TODO: Background segmentation
        }

        findViewById<Button>(R.id.btnExport).setOnClickListener {
            Toast.makeText(this, "Exporting reel...", Toast.LENGTH_SHORT).show()
            // TODO: Export logic
        }
    }

    private fun loadLUTOptions() {
        val lutNames = listOf("Warm", "Cool", "Vintage", "Cinematic")
        lutNames.forEach { name ->
            val button = Button(this).apply {
                text = name
                setOnClickListener {
                    Toast.makeText(this@EditVideoActivity, "Applied $name LUT", Toast.LENGTH_SHORT).show()
                    // TODO: Apply LUT filter
                }
            }
            lutContainer.addView(button)
        }
    }
}
