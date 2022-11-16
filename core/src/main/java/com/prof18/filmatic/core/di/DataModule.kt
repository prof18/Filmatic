package com.prof18.filmatic.core.di

import android.content.Context
import android.content.SharedPreferences
import com.prof18.filmatic.libraries.preferences.Preferences
import com.prof18.filmatic.libraries.preferences.UserPreferences
import com.prof18.filmatic.libraries.preferences.UserPreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun getUserPreferencesManager(sharedPreferences: SharedPreferences): UserPreferences =
        UserPreferencesImpl(sharedPreferences)

    @Provides
    @Singleton
    fun getUserSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.applicationContext.getSharedPreferences(
            Preferences.USER_PREFERENCES,
            Context.MODE_PRIVATE,
        )
}
