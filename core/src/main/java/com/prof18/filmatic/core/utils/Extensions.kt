package com.prof18.filmatic.core.utils

import android.view.View
import com.prof18.filmatic.core.net.NetConstants

fun String.getFullImageUrl(): String =
    NetConstants.basePictureAddress + this

fun View.visibile() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}
