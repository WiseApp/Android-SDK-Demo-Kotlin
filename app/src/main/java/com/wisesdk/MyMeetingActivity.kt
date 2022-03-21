package com.wisesdk

import us.zoom.sdk.MeetingActivity

class MyMeetingActivity : MeetingActivity() {
    override fun onBackPressed() {
        finish()
    }
}