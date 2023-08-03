package com.prof18.filmatic.features.about

import android.app.Activity
import android.content.Intent
import com.prof18.filmatic.core.utils.launchActivity
import com.prof18.filmatic.features.about.contract.AboutContract
import javax.inject.Inject

internal class AboutContractImpl @Inject constructor(): AboutContract {
    override fun launch(currentActivity: Activity) {
        currentActivity.launchActivity(AboutActivity::class.java)
    }
}
