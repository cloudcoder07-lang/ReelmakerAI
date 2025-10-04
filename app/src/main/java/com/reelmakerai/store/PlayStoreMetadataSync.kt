package com.reelmakerai.store

import android.util.Log
import com.reelmakerai.branding.PackUpdateManager
import com.reelmakerai.seo.SeoTagGenerator

object PlayStoreMetadataSync {

    fun syncMetadata() {
        val pack = PackUpdateManager.getCurrentPack()
        val tags = SeoTagGenerator.generateTags(pack.name)
        Log.d("PlayStoreSync", "Tags: ${tags.joinToString()}")
        // TODO: Push to Play Console API if available
    }
}
