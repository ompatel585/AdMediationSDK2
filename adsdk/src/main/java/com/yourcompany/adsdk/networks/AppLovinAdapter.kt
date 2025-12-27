package com.yourcompany.adsdk.networks

import android.app.Activity
import android.app.Application
import android.view.View
import com.applovin.sdk.AppLovinSdk
import com.yourcompany.adsdk.core.*

class AppLovinAdapter(
    override val config: AdNetworkConfig
) : AdNetwork {

    override fun initialize(application: Application) {
        AppLovinSdk.getInstance(application).initializeSdk {}
    }

    override fun loadAd(activity: Activity, adType: AdType, callback: LoadCallback) {
        callback.onLoaded()
    }

    override fun showAd(activity: Activity) {}

    override fun getBannerView(activity: Activity): View? {
        return null
    }
}
