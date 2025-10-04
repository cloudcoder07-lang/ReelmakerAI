package com.reelmakerai.ui

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.reelmakerai.R

class MainBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_board)

        // Set dynamic "What's New" label
        val whatsNewLabel = getString(R.string.whats_new) + " Bloom Pack"
        findViewById<TextView>(R.id.whatsNewText)?.text = whatsNewLabel

        findViewById<android.view.View>(R.id.btnVideo).setOnClickListener {
            Toast.makeText(this, "Create Video clicked", Toast.LENGTH_SHORT).show()
            // TODO: Launch video creation flow
        }

        findViewById<android.view.View>(R.id.btnPhoto).setOnClickListener {
            Toast.makeText(this, "Create Photo clicked", Toast.LENGTH_SHORT).show()
            // TODO: Launch photo editor
        }

        findViewById<android.view.View>(R.id.btnCollage).setOnClickListener {
            Toast.makeText(this, "Create Collage clicked", Toast.LENGTH_SHORT).show()
            // TODO: Launch collage editor
        }
    }
    private lateinit var idleRefresh: IdleRefreshTrigger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_board)

        val packLabel = com.reelmakerai.branding.PackUpdateManager.getCurrentPack().name
        findViewById<TextView>(R.id.whatsNewText)?.text = "WHAT'S NEW: $packLabel"

        idleRefresh = IdleRefreshTrigger {
            findViewById<View>(R.id.refreshOverlay)?.visibility = View.VISIBLE
            recreate()
        }
        idleRefresh.start()
    }

}
