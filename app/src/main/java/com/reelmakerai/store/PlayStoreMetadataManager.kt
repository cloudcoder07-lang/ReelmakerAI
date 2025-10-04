package com.reelmakerai.store

import android.util.Log
import com.reelmakerai.release.VersionInfo
import com.reelmakerai.release.ChangelogGenerator

object PlayStoreMetadataManager {

    fun syncMetadata() {
        val version = VersionInfo.currentVersion
        val changelog = ChangelogGenerator.generate()
        Log.d("PlayStoreSync", "Version: $version\nChangelog:\n$changelog")
        // TODO: Push to Play Console API if available
    }
}
