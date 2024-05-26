package com.prof18.filmatic.features.home

import android.app.Activity
import android.content.Intent
import com.prof18.filmatic.features.home.contract.HomeContract
import com.prof18.filmatic.features.home.presentation.HomeActivity
import javax.inject.Inject

class HomeContractImpl @Inject constructor() : HomeContract {
    override fun getIntent(currentActivity: Activity): Intent =
        Intent(currentActivity, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NO_ANIMATION
        }
}
