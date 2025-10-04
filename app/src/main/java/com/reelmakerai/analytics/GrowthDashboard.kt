package com.reelmakerai.analytics

data class PackStats(
    val name: String,
    val views: Int,
    val unlocks: Int,
    val exports: Int
)

object GrowthDashboard {

    val stats = mutableListOf<PackStats>()

    fun recordStat(stat: PackStats) {
        stats.add(stat)
    }

    fun getSummary(): List<PackStats> = stats
}
