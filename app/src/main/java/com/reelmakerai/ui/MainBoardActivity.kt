package com.reelmakerai.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import com.reelmakerai.R
import com.reelmakerai.analytics.*
import com.reelmakerai.assets.*
import com.reelmakerai.branding.PackUpdateManager
import com.reelmakerai.localization.LocalizationManager
import com.reelmakerai.network.NetworkStatusMonitor
import com.reelmakerai.referral.ReferralEngine
import com.reelmakerai.release.*
import com.reelmakerai.session.SessionManager
import com.reelmakerai.share.ShareManager
import com.reelmakerai.analytics.GrowthDashboard
import com.reelmakerai.ui.launcher.FileEditHistoryActivity
import kotlinx.coroutines.launch
import java.io.File

class MainBoardActivity : AppCompatActivity() {

    private lateinit var engagementMonitor: EngagementMonitor
    private lateinit var idleRefresh: IdleRefreshTrigger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocalizationManager.setLocale(this, "en")
        setContentView(R.layout.activity_main_board)

        SessionManager.startSession()
        AnalyticsTracker.logEvent(EventType.APP_LAUNCH)

        val packLabel = PackUpdateManager.getCurrentPack().name
        findViewById<TextView>(R.id.whatsNewText)?.text = "WHAT'S NEW: $packLabel"

        idleRefresh = IdleRefreshTrigger {
            findViewById<View>(R.id.refreshOverlay)?.visibility = View.VISIBLE
            recreate()
        }
        idleRefresh.start()

        engagementMonitor = EngagementMonitor {
            findViewById<View>(R.id.engagementOverlay)?.visibility = View.VISIBLE
        }
        engagementMonitor.start()

        // ✅ Updated routing for "Create New Video"
        findViewById<CardView>(R.id.btnVideo)?.setOnClickListener {
            AnalyticsTracker.logEvent(EventType.BUTTON_TAPPED, "Video")
            launchFileEditHistory()
        }

        findViewById<CardView>(R.id.btnPhoto)?.setOnClickListener {
            AnalyticsTracker.logEvent(EventType.BUTTON_TAPPED, "Photo")
            MediaLauncher.launchPhotoEditor(this)
        }

        findViewById<CardView>(R.id.btnCollage)?.setOnClickListener {
            AnalyticsTracker.logEvent(EventType.BUTTON_TAPPED, "Collage")
            MediaLauncher.launchCollageBuilder(this)
        }

        findViewById<Button>(R.id.btnInvite)?.setOnClickListener {
            AnalyticsTracker.logEvent(EventType.BUTTON_TAPPED, "Invite")
            ReferralEngine.inviteFriend(this)
        }

        findViewById<Button>(R.id.btnShare)?.setOnClickListener {
            AnalyticsTracker.logEvent(EventType.BUTTON_TAPPED, "Share")
            val dummyUri = Uri.parse("file://dummy.mp4")
            ShareManager.shareVideo(this, dummyUri)
        }

        ThumbnailInjector.inject(this)

        val container = findViewById<FrameLayout>(R.id.dynamicUiContainer)
        val welcomeView = AiUiGenerator.generateWelcomeView(this)
        container.addView(welcomeView)

        val changelog = ChangelogGenerator.generate()
        findViewById<TextView>(R.id.changelogText)?.text = changelog

        val summary = GrowthDashboard.getSummary()
        val summaryText = summary.joinToString("\n") { metric ->
            "${metric.name}: Views=${metric.views}, Unlocks=${metric.unlocks}, Exports=${metric.exports}"
        }
        findViewById<TextView>(R.id.summaryContent)?.text = summaryText

        val isOnline = NetworkStatusMonitor.isOnline(this)
        val banner = findViewById<View>(R.id.offlineBanner)
        val message = findViewById<TextView>(R.id.offlineMessage)
        if (!isOnline) {
            FallbackUiController.showOfflineBanner(banner, message)
        }

        lifecycleScope.launch {
            val success = AssetSyncManager.syncFromGitHub(this@MainBoardActivity)
            val file = File(filesDir, "manifest.json")
            val valid = ManifestValidator.validate(file)
            if (success && valid) {
                ReleaseLock.unlock()
            }
        }

        val ready = ReleaseChecklist.validate()
        LaunchReady.isReady = ready
    }

    private fun launchFileEditHistory() {
        val intent = Intent(this, FileEditHistoryActivity::class.java)
        startActivity(intent)
    }
    override fun onDestroy() {
        super.onDestroy()
        SessionManager.endSession()
        idleRefresh.stop()
        engagementMonitor.stop()
    }
}
