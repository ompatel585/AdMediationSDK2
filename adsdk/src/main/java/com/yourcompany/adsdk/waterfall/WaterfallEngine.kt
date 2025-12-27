package com.yourcompany.adsdk.waterfall

import android.app.Activity
import com.yourcompany.adsdk.core.AdType
import com.yourcompany.adsdk.core.LoadCallback
import com.yourcompany.adsdk.networks.AdNetwork
import java.util.ArrayList
import java.util.Collections
import java.util.Comparator

class WaterfallEngine(networks: List<AdNetwork>) {

    private val sortedNetworks: List<AdNetwork>

    init {
        val temp = ArrayList<AdNetwork>()
        temp.addAll(networks)

        Collections.sort(
            temp,
            object : Comparator<AdNetwork> {
                override fun compare(
                    a: AdNetwork,
                    b: AdNetwork
                ): Int {
                    // higher eCPM first
                    return b.config.ecpm.compareTo(a.config.ecpm)
                }
            }
        )

        sortedNetworks = temp
    }

    fun loadAd(
        activity: Activity,
        adType: AdType,
        onSuccess: (AdNetwork) -> Unit,
        onFail: () -> Unit
    ) {
        tryNext(0, activity, adType, onSuccess, onFail)
    }

    private fun tryNext(
        index: Int,
        activity: Activity,
        adType: AdType,
        onSuccess: (AdNetwork) -> Unit,
        onFail: () -> Unit
    ) {
        if (index >= sortedNetworks.size) {
            onFail()
            return
        }

        val network = sortedNetworks[index]
        network.loadAd(activity, adType, object : LoadCallback {
            override fun onLoaded() {
                onSuccess(network)
            }

            override fun onFailed(error: String) {
                tryNext(index + 1, activity, adType, onSuccess, onFail)
            }
        })
    }
}
