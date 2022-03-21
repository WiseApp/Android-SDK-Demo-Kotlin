package com.wisesdk

import android.app.Application
import android.util.Log
import com.wise.sdk.core.WiseSDK

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initWiseSdk()
    }

    /**
     * Initialise should be called as soon as app starts
     * */
    private fun initWiseSdk() {
        WiseSDK.getInstance(this).initialize(object : WiseSDK.WiseSDKInitListener {
            override fun onInitialised() {
                // sdk initialised successfully
            }

            override fun onVendorIdError() {
                // missing vendor id - make sure vendor id is configured properly
            }
        })
    }
}