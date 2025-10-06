package com.reelmakerai.branding

import android.content.Context
import com.reelmakerai.R

object BrandingUtils {

    fun getPackColor(context: Context, packName: String): Int {
        return when (packName) {
            "Starter Pack" -> context.getColor(R.color.starterPackColor)
            "Pro Pack" -> context.getColor(R.color.proPackColor)
            else -> context.getColor(R.color.defaultPackColor)
        }
    }

    fun getPackTag(packName: String): String {
        return when (packName) {
            "Starter Pack" -> "#starter"
            "Pro Pack" -> "#pro"
            else -> "#reelmaker"
        }
    }
}
