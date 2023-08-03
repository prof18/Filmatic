package com.prof18.filmatic.core.utils

import android.app.Activity
import android.content.Intent
import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun <T : Activity> Activity.launchActivity(clazz: Class<T>) {
    startActivity(
        Intent(this, clazz)
            .setAction(Intent.ACTION_VIEW)
    )
}