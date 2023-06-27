package com.wisesdk

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.mine.R
import com.wise.sdk.core.WiseSDK

class MainActivity : AppCompatActivity(), WiseSDK.WiseSDKMeetingListener {
    private val tag = MainActivity::class.java.simpleName

    // classroom public ID / magic token
    private val classPublicID = "649a8a5cbc098a31f0808bfc35114999"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Joining a live meeting
         * */
        findViewById<Button>(R.id.btn_join_meeting).setOnClickListener {
            // goto meeting if it's in progress
            if (WiseSDK.getInstance(this).isMeetingInProgress()) {
                WiseSDK.getInstance(this).returnToMeeting()
            } else {
                // join meeting
                WiseSDK.getInstance(this)
                    .joinMeeting(
                        token = classPublicID,
                        userId = "9499900002",
                        displayName = "Mr John",
                        listener = this
                    )
            }
        }
    }

    override fun onMeetingNotStartedByHostError() {
        Log.d(tag, "onMeetingNotStartedByHostError")
    }

    override fun onInitialised() {
        Log.d(tag, "On SDK Initialised")
    }

    override fun onMeetingConnecting() {
        Log.d(tag, "On connecting to meeting")
    }

    override fun onMeetingEnded(userId: String?) {
        Log.d(tag, "Meeting ended. UserID: $userId")
    }

    override fun onMeetingStarted(isInMeeting: Boolean) {
        Log.d(tag, "onMeetingStarted isInMeeting: $isInMeeting")
    }

    override fun onSDKError(wiseErrorCode: Int, errorCode: Int, internalErrorCode: Int) {
        Log.d(tag, "onSDKError errorCode: $errorCode")
    }

    override fun onVendorIdError() {
        Log.d(tag, "Missing vendor ID")
    }

    override fun onMeetingEndedByHost() {
        Log.d(tag, "onMeetingEndedByHost")
    }

    override fun onMeetingEndedWithError(message: String, errorCode: Int, internalErrorCode: Int) {
        Log.d(
            tag,
            "Meeting ended with error $message, errorCode: $errorCode, internalErrorCode: $internalErrorCode"
        )
    }

    override fun onMeetingNeedPasswordOrDisplayName() {
        Log.d(tag, "onMeetingNeedPasswordOrDisplayName")
    }
}