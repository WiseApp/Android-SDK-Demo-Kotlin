package com.wisesdk

import android.app.Application
import android.util.Log
import com.mine.R
import com.wise.sdk.core.WiseSDK

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initWiseSdk()
    }

    private fun initWiseSdk() {
        WiseSDK.getInstance(this).apply {
            // set vendor Id and namespace
            // Send an email to info@wiseapp.live to get your VENDOR_ID and NAMESPACE
            // to be used in the Android Integration steps below
            init("5f72e04f121699872486dc80", "wise")

            // custom lens icon
            setLensIcon(R.mipmap.ic_launcher)
        }
    }
}