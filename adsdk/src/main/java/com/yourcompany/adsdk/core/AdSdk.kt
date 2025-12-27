package com.yourcompany.adsdk.core

import com.yourcompany.adsdk.networks.AdNetwork
import android.app.Application
import java.util.ArrayList

object AdSdk {

    private val networks: ArrayList<AdNetwork> = ArrayList()

    fun init(application: Application, adNetworks: List<AdNetwork>) {
        networks.clear()

        var i = 0
        while (i < adNetworks.size) {
            networks.add(adNetworks[i])
            i++
        }

        var j = 0
        while (j < networks.size) {
            networks[j].initialize(application)
            j++
        }
    }

    fun getNetworks(): List<AdNetwork> {
        return networks
    }
}
