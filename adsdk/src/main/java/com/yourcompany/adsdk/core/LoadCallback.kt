package com.yourcompany.adsdk.core

interface LoadCallback {
    fun onLoaded()
    fun onFailed(error: String)
}
