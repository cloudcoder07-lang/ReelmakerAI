package com.reelmakerai.billing

import android.content.Context
import com.android.billingclient.api.*

object ProAccessManager {

    private var billingClient: BillingClient? = null
    private var isPro = false

    fun init(context: Context) {
        billingClient = BillingClient.newBuilder(context)
            .setListener { billingResult, purchases ->
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
                    isPro = purchases.any { it.skus.contains("reelmaker_pro") }
                }
            }
            .enablePendingPurchases()
            .build()

        billingClient?.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(result: BillingResult) {}
            override fun onBillingServiceDisconnected() {}
        })
    }

    fun userHasProAccess(): Boolean = isPro
}
