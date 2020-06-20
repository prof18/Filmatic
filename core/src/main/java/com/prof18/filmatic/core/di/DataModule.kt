/*
 * Copyright 2019 Marco Gomiero
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.prof18.filmatic.core.di

import android.content.Context
import android.content.SharedPreferences
import com.prof18.filmatic.libraries.preferences.Preferences
import com.prof18.filmatic.libraries.preferences.UserPreferences
import com.prof18.filmatic.libraries.preferences.UserPreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
class DataModule {

    @Provides
    @Singleton
    fun getUserPreferencesManager(sharedPreferences: SharedPreferences): UserPreferences =
        UserPreferencesImpl(sharedPreferences)

    @Provides
    @Singleton
    fun getUserSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.applicationContext.getSharedPreferences(
            Preferences.USER_PREFERENCES,
            Context.MODE_PRIVATE
        )
}