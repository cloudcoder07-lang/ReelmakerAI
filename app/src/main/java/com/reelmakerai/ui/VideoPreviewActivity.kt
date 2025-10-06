package com.reelmakerai.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.reelmakerai.R

class VideoPreviewActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 2001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_preview)

        if (hasPermissions()) {
            launchVoicePreview()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA),
                REQUEST_CODE_PERMISSIONS
            )
        }
    }

    private fun hasPermissions(): Boolean {
        val mic = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
        val cam = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        return mic == PackageManager.PERMISSION_GRANTED && cam == PackageManager.PERMISSION_GRANTED
    }

    private fun launchVoicePreview() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.previewContainer, com.reelmakerai.preview.`VoiceFxPreviewFragment-old`())
            .commit()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            launchVoicePreview()
        } else {
            Toast.makeText(this, "Permissions required for preview", Toast.LENGTH_SHORT).show()
        }
    }
}
