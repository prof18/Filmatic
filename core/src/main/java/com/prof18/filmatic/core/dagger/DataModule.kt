package com.prof18.filmatic.core.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.prof18.filmatic.core.UserPreferenceManager
import com.prof18.filmatic.core.preference.Preferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule(private val application: Application) {

    @Provides
    @Singleton
    fun getUserPreferencesManager(sharedPreferences: SharedPreferences): UserPreferenceManager =
        UserPreferenceManager(sharedPreferences)

    @Provides
    fun getUserSharedPreferences(context: Context): SharedPreferences =
        context.applicationContext.getSharedPreferences(
            Preferences.USER_PREFERENCES,
            Context.MODE_PRIVATE
        )

    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext
}