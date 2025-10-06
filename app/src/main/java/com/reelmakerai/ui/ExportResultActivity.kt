package com.reelmakerai.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R
import com.reelmakerai.social.ShareManager
import com.reelmakerai.social.ReferralEngine

class ExportResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export_result)

        val videoView = findViewById<VideoView>(R.id.finalVideoView)
        val outputUri = intent.getParcelableExtra<Uri>("outputUri")

        if (outputUri != null) {
            videoView.setVideoURI(outputUri)
            videoView.setOnPreparedListener {
                it.setVolume(1f, 1f)
                it.start()
                findViewById<View>(R.id.finalExportOverlay)?.visibility = View.VISIBLE
            }
            videoView.start()
        }

        findViewById<Button>(R.id.btnShare)?.setOnClickListener {
            outputUri?.let { uri ->
                ShareManager.shareVideo(this, uri)
            }
        }

        findViewById<Button>(R.id.btnInvite)?.setOnClickListener {
            ReferralEngine.inviteFriend(this)
        }
    }
}
