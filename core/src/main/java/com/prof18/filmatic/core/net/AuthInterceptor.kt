package com.prof18.filmatic.core.net

import com.prof18.filmatic.core.BuildConfig
import com.prof18.filmatic.core.UserPreferenceManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor : Interceptor {

    @Inject
    lateinit var userPrefManager: UserPreferenceManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.TMDB_KEY)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}