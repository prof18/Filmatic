package com.prof18.filmatic.libraries.preferences.di

import android.content.Context
import android.content.SharedPreferences
import com.m2f.archer.crud.GetRepository
import com.m2f.archer.crud.PutRepository
import com.m2f.archer.crud.cache.cache
import com.m2f.archer.crud.operation.StoreSyncOperation
import com.m2f.archer.datasource.extensions.toDataSource
import com.m2f.archer.repository.toRepository
import com.prof18.filmatic.libraries.preferences.Preferences
import com.prof18.filmatic.core.architecture.PrefsField
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PreferencesModule {

    @Provides
    @Singleton
    fun providesGetUserRepositories(
        sharedPreferences: SharedPreferences,
    ): @JvmSuppressWildcards GetRepository<PrefsField, String> =
        sharedPreferences
            .toDataSource<PrefsField, String>()
            .cache()
            .create(StoreSyncOperation)

    @Provides
    @Singleton
    fun providesSetUserRepositories(
        sharedPreferences: SharedPreferences,
    ): @JvmSuppressWildcards PutRepository<PrefsField, String> =
        sharedPreferences
            .toDataSource<PrefsField, String>()
            .toRepository()


    @Provides
    @Singleton
    fun getUserSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.applicationContext.getSharedPreferences(
            Preferences.USER_PREFERENCES,
            Context.MODE_PRIVATE,
        )
}
