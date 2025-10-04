package com.reelmakerai.ui

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R

class ExportResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export_result)

        val videoView = findViewById<VideoView>(R.id.finalVideoView)
        val outputUri = intent.getParcelableExtra<Uri>("outputUri")
        videoView.setVideoURI(outputUri)
        videoView.start()

        videoView.setOnPreparedListener {
            it.setVolume(1f, 1f)
            it.start()
            findViewById<View>(R.id.finalExportOverlay)?.visibility = View.VISIBLE
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export_result)

        val videoView = findViewById<VideoView>(R.id.finalVideoView)
        val outputUri = intent.getParcelableExtra<Uri>("outputUri")
        videoView.setVideoURI(outputUri)
        videoView.start()

        findViewById<Button>(R.id.btnShare)?.setOnClickListener {
            outputUri?.let { uri ->
                com.reelmakerai.social.ShareManager.shareVideo(this, uri)
            }
        }

        findViewById<Button>(R.id.btnInvite)?.setOnClickListener {
            com.reelmakerai.social.ReferralEngine.inviteFriend(this)
        }
    }

}
