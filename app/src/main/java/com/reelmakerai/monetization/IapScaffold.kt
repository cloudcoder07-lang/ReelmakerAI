package com.reelmakerai.monetization

import android.content.Context
import android.util.Log

object IapScaffold {

    fun purchasePack(context: Context, packName: String) {
        Log.d("IAP", "Simulating purchase for $packName")
        PackUnlockManager.unlock(packName)
    }
}
