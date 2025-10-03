package com.reelmakerai.pro

object ProFeatureManager {

    var isProUser: Boolean = false // Set this based on actual unlock logic

    private val gatedFeatures = listOf("voice_fx", "watermark_free", "export_hd")

    fun isFeatureLocked(feature: String): Boolean {
        return !isProUser && feature in gatedFeatures
    }

    fun isUnlocked(feature: String): Boolean {
        return !isFeatureLocked(feature)
    }

    fun getLockedMessage(feature: String): String {
        return when (feature) {
            "voice_fx" -> "Voice FX is a Pro feature."
            "watermark_free" -> "Removing watermark requires Pro access."
            "export_hd" -> "HD export is available for Pro users."
            else -> "This feature requires Pro access."
        }
    }

    fun getAllLockedFeatures(): List<String> {
        return if (isProUser) emptyList() else gatedFeatures
    }
}
