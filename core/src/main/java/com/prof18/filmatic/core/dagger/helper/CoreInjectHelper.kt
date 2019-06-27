package com.prof18.filmatic.core.dagger.helper

import android.content.Context
import com.prof18.filmatic.core.dagger.CoreComponent

object CoreInjectHelper {
    fun provideCoreComponent(applicationContext: Context): CoreComponent {
        return if (applicationContext is CoreComponentProvider) {
            (applicationContext as CoreComponentProvider).provideCoreComponent()
        } else {
            throw IllegalStateException("The application context you have passed does not implement CoreComponentProvider")
        }
    }

}