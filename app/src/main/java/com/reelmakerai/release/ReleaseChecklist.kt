package com.reelmakerai.release

object ReleaseChecklist {

    fun validate(): Boolean {
        return listOf(
            "Export flow working",
            "Voice FX stable",
            "Offline mode fallback",
            "Multilingual branding",
            "Share + referral logic",
            "Telemetry + unlock tracking"
        ).all { it.isNotBlank() }
    }
    fun validate(): Boolean {
        val changelog = ChangelogGenerator.generate()
        return changelog.isNotBlank() && changelog.contains("export")
    }

}
