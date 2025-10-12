package com.reelmakerai.ui.editor

import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R

class FlipEditorActivity : AppCompatActivity() {

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
            Toast.makeText(this, "Flip exported", Toast.LENGTH_SHORT).show()
        }

        btnClose.setOnClickListener {
            finish()
        }

        injectFlipControls()
    }

    private fun injectFlipControls() {
        val flipControls = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            val btnFlipHorizontal = Button(context).apply {
                text = "Flip Horizontal"
                setBackgroundColor(0x00000000)
                setTextColor(0xFFFFFFFF.toInt())
                setOnClickListener {
                    Toast.makeText(context, "Flipped horizontally", Toast.LENGTH_SHORT).show()
                }
            }
            val btnFlipVertical = Button(context).apply {
                text = "Flip Vertical"
                setBackgroundColor(0x00000000)
                setTextColor(0xFFFFFFFF.toInt())
                setOnClickListener {
                    Toast.makeText(context, "Flipped vertically", Toast.LENGTH_SHORT).show()
                }
            }
            addView(btnFlipHorizontal)
            addView(btnFlipVertical)
        }

        toolControlContainer.addView(flipControls)
    }
}
