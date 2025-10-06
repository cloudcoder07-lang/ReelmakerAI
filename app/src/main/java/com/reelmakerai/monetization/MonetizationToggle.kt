package com.reelmakerai.monetization

object MonetizationToggle {

    var adsEnabled: Boolean = true

    fun isEnabled(): Boolean = adsEnabled

    fun disable() {
        adsEnabled = false
    }

    fun enable() {
        adsEnabled = true
    }
}
