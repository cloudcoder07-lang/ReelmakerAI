package com.reelmakerai.monetization

object PackUnlockManager {

    private val unlockedPacks = mutableSetOf<String>()

    fun isUnlocked(packName: String): Boolean {
        return unlockedPacks.contains(packName)
    }

    fun unlock(packName: String) {
        unlockedPacks.add(packName)
        com.reelmakerai.analytics.UnlockTelemetry.logUnlock(packName, "manual")
    }

}
