package com.reelmakerai.seo

object SeoTagGenerator {

    fun generateTags(packName: String): List<String> {
        return listOf(
            "ReelMakerAI",
            packName,
            "$packName video editor",
            "$packName voice FX",
            "AI video app",
            "cinematic reels"
        )
    }
}
