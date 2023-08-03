package com.prof18.filmatic.features.home.contract

import android.app.Activity
import android.content.Intent

interface HomeContract {
    fun getIntent(currentActivity: Activity): Intent
}
