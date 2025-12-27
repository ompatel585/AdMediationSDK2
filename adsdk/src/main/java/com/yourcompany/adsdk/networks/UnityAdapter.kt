package com.yourcompany.adsdk.networks

import android.app.Activity
import android.app.Application
import android.view.View

import com.unity3d.ads.UnityAds
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.IUnityAdsLoadListener

import com.yourcompany.adsdk.core.*

class UnityAdapter(
    override val config: AdNetworkConfig,
    private val gameId: String,
    private val placementId: String
) : AdNetwork {

    override fun initialize(application: Application) {
        UnityAds.initialize(
            application,
            gameId,
            false,
            object : IUnityAdsInitializationListener {
                override fun onInitializationComplete() {
                    // Unity initialized
                }

                override fun onInitializationFailed(
                    error: UnityAds.UnityAdsInitializationError,
                    message: String
                ) {
                    // init failed
                }
            }
        )
    }

    override fun loadAd(
        activity: Activity,
        adType: AdType,
        callback: LoadCallback
    ) {
        UnityAds.load(
            placementId,
            object : IUnityAdsLoadListener {

                override fun onUnityAdsAdLoaded(id: String) {
                    callback.onLoaded()
                }

                override fun onUnityAdsFailedToLoad(
                    id: String,
                    error: UnityAds.UnityAdsLoadError,
                    message: String
                ) {
                    callback.onFailed(message)
                }
            }
        )
    }

    override fun showAd(activity: Activity) {

            UnityAds.show(activity, placementId)
    }

    override fun getBannerView(activity: Activity): View? {
        return null
    }
}
