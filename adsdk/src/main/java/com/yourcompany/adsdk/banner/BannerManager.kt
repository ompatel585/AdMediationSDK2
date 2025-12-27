package com.yourcompany.adsdk.banner

import android.app.Activity
import android.view.ViewGroup
import com.yourcompany.adsdk.core.AdType
import com.yourcompany.adsdk.waterfall.WaterfallEngine

class BannerManager(private val engine: WaterfallEngine) {

    fun load(activity: Activity, container: ViewGroup) {
        engine.loadAd(activity, AdType.BANNER, { network ->
            container.removeAllViews()
            val banner = network.getBannerView(activity)
            if (banner != null) {
                container.addView(banner)
            }
        }) {}
    }
}
