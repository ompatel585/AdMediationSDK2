package com.yourcompany.adsdk.rewarded

import android.app.Activity
import com.yourcompany.adsdk.core.AdType
import com.yourcompany.adsdk.waterfall.WaterfallEngine

class RewardedManager(private val engine: WaterfallEngine) {

    fun loadAndShow(activity: Activity) {
        engine.loadAd(activity, AdType.REWARDED, { network ->
            network.showAd(activity)
        }) {}
    }
}
