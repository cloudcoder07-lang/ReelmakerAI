package com.reelmakerai.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R
import com.reelmakerai.export.ExportManager
import java.io.File

class ExportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export)

        val videoInput = File(filesDir, "temp_video.mp4")
        val audioInput = File(filesDir, "temp_audio.wav")
        val outputFile = File(filesDir, "final_output.mp4")

        val success = ExportManager().exportFinalVideo(videoInput, audioInput, outputFile)

        Toast.makeText(this,
            if (success) "Export complete!" else "Export failed.",
            Toast.LENGTH_SHORT).show()
    }
}
