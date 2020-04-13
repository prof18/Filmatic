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

package com.prof18.filmatic.core.dagger

import com.prof18.filmatic.core.BuildConfig
import com.prof18.filmatic.core.net.AuthInterceptor
import com.prof18.filmatic.libraries.preferences.UserPreferences
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class CoreModule {

    @Provides
    fun provideOkHttpClient(
        interceptor: AuthInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(httpLoggingInterceptor)
            .build()


    @Provides
    fun provideAuthInterceptor(userPreferences: UserPreferences): AuthInterceptor =
        AuthInterceptor(userPreferences)

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
}