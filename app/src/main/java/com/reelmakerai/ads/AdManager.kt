package com.reelmakerai.ads

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

object AdManager {

    fun loadBannerAd(context: Context, container: ViewGroup) {
        MobileAds.initialize(context)
        val adView = AdView(context)
        adView.adUnitId = "ca-app-pub-xxxxxxxxxxxxxxxx/banner"
        adView.adSize = com.google.android.gms.ads.AdSize.BANNER
        container.addView(adView)
        adView.loadAd(AdRequest.Builder().build())
    }
}
