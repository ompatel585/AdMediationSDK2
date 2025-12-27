package com.yourcompany.adsdk.networks

import android.app.Activity
import android.app.Application
import android.view.View

import com.ironsource.mediationsdk.IronSource

import com.yourcompany.adsdk.core.*

class IronSourceAdapter(
    override val config: AdNetworkConfig,
    private val appKey: String
) : AdNetwork {

    override fun initialize(application: Application) {
        IronSource.init(application, appKey)
    }

    override fun loadAd(
        activity: Activity,
        adType: AdType,
        callback: LoadCallback
    ) {
        // IronSource auto-loads internally
        callback.onLoaded()
    }

    override fun showAd(activity: Activity) {
        if (IronSource.isInterstitialReady()) {
            IronSource.showInterstitial()
        }
    }

    override fun getBannerView(activity: Activity): View? {
        return null
    }
}
