package com.reelmakerai.legal

object LicenseValidator {

    fun isAssetCompliant(assetName: String): Boolean {
        return !assetName.contains("disney", ignoreCase = true) &&
                !assetName.contains("marvel", ignoreCase = true) &&
                !assetName.contains("nintendo", ignoreCase = true)
    }
}
