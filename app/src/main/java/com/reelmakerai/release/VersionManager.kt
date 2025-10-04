package com.reelmakerai.release

object VersionManager {

    fun bumpPatch(version: String): String {
        val parts = version.split(".").map { it.toInt() }
        return "${parts[0]}.${parts[1]}.${parts[2] + 1}"
    }

    fun bumpMinor(version: String): String {
        val parts = version.split(".").map { it.toInt() }
        return "${parts[0]}.${parts[1] + 1}.0"
    }

    fun bumpMajor(version: String): String {
        val parts = version.split(".").map { it.toInt() }
        return "${parts[0] + 1}.0.0"
    }
}
