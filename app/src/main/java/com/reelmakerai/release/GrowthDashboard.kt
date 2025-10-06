package com.reelmakerai.release

data class GrowthMetric(
    val name: String,
    val views: Int,
    val unlocks: Int,
    val exports: Int
)

object GrowthDashboard {
    fun getSummary(): List<GrowthMetric> {
        return listOf(
            GrowthMetric("Cinematic", views = 1200, unlocks = 300, exports = 150),
            GrowthMetric("Vintage", views = 800, unlocks = 200, exports = 90),
            GrowthMetric("Epic", views = 950, unlocks = 250, exports = 110)
        )
    }
}
