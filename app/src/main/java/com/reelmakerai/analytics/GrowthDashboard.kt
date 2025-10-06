package com.reelmakerai.analytics

data class GrowthMetric(val name: String, val views: Int, val unlocks: Int, val exports: Int)

object GrowthDashboard {
    fun getSummary(): List<GrowthMetric> {
        return listOf(
            GrowthMetric("Cinematic", 1200, 300, 150),
            GrowthMetric("Vintage", 800, 200, 90),
            GrowthMetric("Epic", 950, 250, 110)
        )
    }
}
