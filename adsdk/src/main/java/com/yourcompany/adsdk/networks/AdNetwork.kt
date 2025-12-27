package com.yourcompany.adsdk.networks

import android.app.Activity
import android.app.Application
import android.view.View
import com.yourcompany.adsdk.core.AdNetworkConfig
import com.yourcompany.adsdk.core.AdType
import com.yourcompany.adsdk.core.LoadCallback

interface AdNetwork {

    val config: AdNetworkConfig

    fun initialize(application: Application)

    fun loadAd(
        activity: Activity,
        adType: AdType,
        callback: LoadCallback
    )

    fun showAd(activity: Activity)

    fun getBannerView(activity: Activity): View?
}
