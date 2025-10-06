package com.reelmakerai.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.reelmakerai.R
import com.reelmakerai.analytics.AnalyticsTracker
import com.reelmakerai.analytics.EventType
import com.reelmakerai.analytics.EngagementMonitor
import com.reelmakerai.assets.AssetSyncManager
import com.reelmakerai.assets.ManifestValidator
import com.reelmakerai.branding.PackUpdateManager
import com.reelmakerai.localization.LocalizationManager
import com.reelmakerai.network.NetworkStatusMonitor
import com.reelmakerai.preview.`VoiceFxPreviewFragment-old`
import com.reelmakerai.referral.ReferralEngine
import com.reelmakerai.release.ChangelogGenerator
import com.reelmakerai.release.GrowthDashboard
import com.reelmakerai.release.LaunchReady
import com.reelmakerai.release.ReleaseChecklist
import com.reelmakerai.release.ReleaseLock
import com.reelmakerai.session.SessionManager
import com.reelmakerai.share.ShareManager
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

        findViewById<Button>(R.id.btnVideo)?.setOnClickListener {
            AnalyticsTracker.logEvent(EventType.BUTTON_TAPPED, "Video")
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, `VoiceFxPreviewFragment-old`())
                .commit()
        }

        findViewById<Button>(R.id.btnInvite)?.setOnClickListener {
            ReferralEngine.inviteFriend(this)
        }

        findViewById<Button>(R.id.btnShare)?.setOnClickListener {
            val dummyUri = Uri.parse("file://dummy.mp4")
            ShareManager.shareVideo(this, dummyUri)
        }

        val container = findViewById<FrameLayout>(R.id.dynamicUiContainer)
        val welcomeView = AiUiGenerator.generateWelcomeView(this)
        container.addView(welcomeView)

        val changelog = ChangelogGenerator.generate()
        findViewById<TextView>(R.id.changelogText)?.text = changelog

        val summary = GrowthDashboard.getSummary()
        val summaryText = summary.joinToString("\n") {
            "${it.name}: Views=${it.views}, Unlocks=${it.unlocks}, Exports=${it.exports}"
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

    override fun onDestroy() {
        super.onDestroy()
        SessionManager.endSession()
        idleRefresh.stop()
        engagementMonitor.stop()
    }
}
