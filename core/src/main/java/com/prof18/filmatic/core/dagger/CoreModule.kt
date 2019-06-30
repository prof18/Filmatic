package com.prof18.filmatic.core.dagger

import com.prof18.filmatic.core.BuildConfig
import com.prof18.filmatic.core.net.AuthInterceptor
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
        OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(httpLoggingInterceptor).build()


    @Provides
    fun provideAuthInterceptor(): AuthInterceptor = AuthInterceptor()

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