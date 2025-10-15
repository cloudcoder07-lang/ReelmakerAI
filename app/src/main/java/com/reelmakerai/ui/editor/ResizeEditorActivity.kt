package com.reelmakerai.ui.editor

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R
import com.reelmakerai.ui.tools.ResizeToolFragment

class ResizeEditorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resize_editor)

        val videoUri = intent.getStringExtra("video_uri")
        if (videoUri != null) {
            val fragment = ResizeToolFragment().apply {
                arguments = Bundle().apply {
                    putString("video_uri", videoUri)
                }
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.resizeToolContainer, fragment)
                .commit()
        }
    }
}
