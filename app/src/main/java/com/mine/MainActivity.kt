package com.mine

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.mine.databinding.ActivityMainBinding
import com.wise.sdk.core.WiseSDK

class MainActivity : AppCompatActivity(), WiseSDK.WiseSDKMeetingListener {
    private val tag = MainActivity::class.java.simpleName

    /**
     * Sample teacher and student tokens.
     * Follow the wise API docs to fetch the required values
     * https://www.notion.so/wiseapp/Wise-API-Documentation-a38e78c9a79d439ca7d13bae9d2d5582
     * */
    private val teacherToken = "5f72e04f121699872486dc8012345678"
    private val studentToken = "5fb8ab8cc91e3444349dc78165404171"

    // classroom ID
    private val classId = "5fb8ab8cc91e3444349dc781"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Starting a new meeting
         * Check for any existing zoom meeting before starting a new one
         * */
        findViewById<Button>(R.id.btn_start_meeting).setOnClickListener {
            // goto meeting if it's in progress
            if (WiseSDK.getInstance(this).isMeetingInProgress()) {
                WiseSDK.getInstance(this).returnToMeeting()
            } else {
                // start meeting
                WiseSDK.getInstance(this).startMeeting(teacherToken, classId, this)
            }
        }

        /**
         * Starting a new meeting with meeting params
         * Check for any existing zoom meeting before starting a new one
         * */
        findViewById<Button>(R.id.btn_start_meeting_with_params).setOnClickListener {
            // goto meeting if it's in progress
            if (WiseSDK.getInstance(this).isMeetingInProgress()) {
                WiseSDK.getInstance(this).returnToMeeting()
            } else {
                // start meeting
                // Replace the below values you get from wise API
                // https://www.notion.so/wiseapp/Wise-API-Documentation-a38e78c9a79d439ca7d13bae9d2d5582
                WiseSDK.getInstance(this).startMeetingWithParams(
                    classId = classId,
                    startUrl = "https://wise-live.zoom.us/s/96979300288?zak=eyJ0eXAiOiJKV1QiLCJzdiI6IjAwMDAwMSIsInptX3NrbSI6InptX28ybSIsImFsZyI6IkhTMjU2In0.eyJhdWQiOiJjbGllbnRzbSIsInVpZCI6IlZsakdmNnpOU2FPRzR2d1p3M0ZhWnciLCJpc3MiOiJ3ZWIiLCJzayI6IjAiLCJzdHkiOjk5LCJ3Y2QiOiJhdzEiLCJjbHQiOjAsIm1udW0iOiI5Njk3OTMwMDI4OCIsImV4cCI6MTY1NDQ5MjMzMiwiaWF0IjoxNjQ2NzE2MzMyLCJhaWQiOiJtZV9CVjB3d1RkLWZmZ3ctakZGZml3IiwiY2lkIjoiIn0.It_dUDsnSI_zHkWuYIGNE_nP0XMtWY09It63p9DQPp4",
                    meetingId = "96979300288",
                    meetingPassword = "666080",
                    zakToken = "eyJ0eXAiOiJKV1QiLCJzdiI6IjAwMDAwMSIsInptX3NrbSI6InptX28ybSIsImFsZyI6IkhTMjU2In0.eyJhdWQiOiJjbGllbnRzbSIsInVpZCI6IlZsakdmNnpOU2FPRzR2d1p3M0ZhWnciLCJpc3MiOiJ3ZWIiLCJzayI6IjAiLCJzdHkiOjk5LCJ3Y2QiOiJhdzEiLCJjbHQiOjAsIm1udW0iOiI5Njk3OTMwMDI4OCIsImV4cCI6MTY1NDQ5MjMzMiwiaWF0IjoxNjQ2NzE2MzMyLCJhaWQiOiJtZV9CVjB3d1RkLWZmZ3ctakZGZml3IiwiY2lkIjoiIn0.It_dUDsnSI_zHkWuYIGNE_nP0XMtWY09It63p9DQPp4",
                    zoomUserId = "VljGf6zNSaOG4vwZw3FaZw",
                    participantIdPrefix = "169316985",
                    canRecordMeeting = true
                )
            }
        }

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
                        token = studentToken,
                        userId = "",
                        vendorUserId = "",
                        displayName = "Student Name",
                        listener = this
                    )
            }
        }
    }

    override fun onMeetingNotStartedByHostError() {
        Log.d(tag, "onMeetingNotStartedByHostError")
    }

    override fun onSDKError(errorCode: Int) {
        Log.d(tag, "onSDKError errorCode: $errorCode")
    }

    override fun onMeetingConnecting() {
        Log.d(tag, "onMeetingConnecting")
    }

    override fun onMeetingStarted(isInMeeting: Boolean) {
        Log.d(tag, "onMeetingStarted isInMeeting: $isInMeeting")
    }

    override fun onMeetingEnded() {
        Log.d(tag, "onMeetingEnded")
    }

    override fun onMeetingEndedByHost() {
        Log.d(tag, "onMeetingEndedByHost")
    }

    override fun onMeetingEndedWithError(message: String) {
        Log.d(tag, "onMeetingEndedWithError. message: $message")
    }

    override fun onMeetingNeedPasswordOrDisplayName() {
        Log.d(tag, "onMeetingNeedPasswordOrDisplayName")
    }
}