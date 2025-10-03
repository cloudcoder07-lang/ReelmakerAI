package com.reelmakerai.filters

object MoodLutMapper {

    fun getLutForMood(mood: String): String {
        return when (mood.lowercase()) {
            "happy" -> "luts/cinematic.cube"
            "sad" -> "luts/noir.cube"
            "angry" -> "luts/intense.cube"
            else -> "luts/default.cube"
        }
    }
}
