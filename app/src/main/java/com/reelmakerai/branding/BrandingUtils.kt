package com.reelmakerai.branding

object BrandingUtils {

    fun getCurrentPackName(): String {
        return "Bloom Pack"
    }

    fun getWhatsNewLabel(): String {
        return "WHAT'S NEW: ${getCurrentPackName()}"
    }
    fun getLocalizedWhatsNew(context: Context): String {
        val pack = PackUpdateManager.getCurrentPack().name
        return context.getString(R.string.whats_new) + " $pack"
    }
}
