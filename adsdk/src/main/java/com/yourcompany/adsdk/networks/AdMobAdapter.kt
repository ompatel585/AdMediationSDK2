package com.yourcompany.adsdk.networks

import android.app.Activity
import android.app.Application
import android.view.View

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds

import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

import com.yourcompany.adsdk.core.*

class AdMobAdapter(
    override val config: AdNetworkConfig,
    private val bannerId: String,
    private val interstitialId: String,
    private val rewardedId: String
) : AdNetwork {

    private var bannerView: AdView? = null
    private var interstitialAd: InterstitialAd? = null
    private var rewardedAd: RewardedAd? = null

    override fun initialize(application: Application) {
        MobileAds.initialize(application)
    }

    override fun loadAd(
        activity: Activity,
        adType: AdType,
        callback: LoadCallback
    ) {
        when (adType) {

            AdType.BANNER -> {
                bannerView = AdView(activity)
                bannerView!!.adUnitId = bannerId
                bannerView!!.setAdSize(AdSize.BANNER)
                bannerView!!.loadAd(AdRequest.Builder().build())
                callback.onLoaded()
            }

            AdType.INTERSTITIAL -> {
                InterstitialAd.load(
                    activity,
                    interstitialId,
                    AdRequest.Builder().build(),
                    object : InterstitialAdLoadCallback() {
                        override fun onAdLoaded(ad: InterstitialAd) {
                            interstitialAd = ad
                            callback.onLoaded()
                        }

                        override fun onAdFailedToLoad(error: LoadAdError) {
                            callback.onFailed(error.message)
                        }
                    }
                )
            }

            AdType.REWARDED -> {
                RewardedAd.load(
                    activity,
                    rewardedId,
                    AdRequest.Builder().build(),
                    object : RewardedAdLoadCallback() {
                        override fun onAdLoaded(ad: RewardedAd) {
                            rewardedAd = ad
                            callback.onLoaded()
                        }

                        override fun onAdFailedToLoad(error: LoadAdError) {
                            callback.onFailed(error.message)
                        }
                    }
                )
            }
        }
    }

    override fun showAd(activity: Activity) {
        interstitialAd?.show(activity)
        rewardedAd?.show(activity) {
            // reward earned
        }
    }

    override fun getBannerView(activity: Activity): View? {
        return bannerView
    }
}
