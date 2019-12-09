package com.prof18.filmatic.core.utils

import android.content.Context
import android.content.Intent

object Actions {
    fun openHomeIntent(context: Context): Intent =
        internalIntent(context, "com.prof18.filmatic.features.home")

    private fun internalIntent(context: Context, action: String) =
        Intent(action).setPackage(context.packageName)
}