package com.prof18.filmatic.libraries.navigation

import android.content.Context
import android.content.Intent

object Destinations {

    fun openAboutActivity(context: Context): Intent = internalIntent(
        context,
        action = "com.prof18.filmatic.features.about",
    )

    // Makes the intent respond to actions only inside the app
    private fun internalIntent(context: Context, action: String) =
        Intent(action).setPackage(context.packageName)
}
