package com.yourcompany.adsdk.interstitial

import android.app.Activity
import com.yourcompany.adsdk.core.AdType
import com.yourcompany.adsdk.waterfall.WaterfallEngine

class InterstitialManager(private val engine: WaterfallEngine) {

    fun loadAndShow(activity: Activity) {
        engine.loadAd(activity, AdType.INTERSTITIAL, { network ->
            network.showAd(activity)
        }) {}
    }
}
