package com.reelmakerai.filters

object LutParser {

    fun parseCubeLut(cubeText: String): List<FloatArray> {
        val lines = cubeText.lines()
        val lutData = mutableListOf<FloatArray>()

        for (line in lines) {
            val trimmed = line.trim()
            if (trimmed.isEmpty() || trimmed.startsWith("#") || trimmed.contains("TITLE") || trimmed.contains("DOMAIN")) continue

            val parts = trimmed.split(" ")
                .filter { it.isNotBlank() }
                .mapNotNull { it.toFloatOrNull() }

            if (parts.size == 3) {
                lutData.add(floatArrayOf(parts[0], parts[1], parts[2]))
            }
        }

        return lutData
    }
}
