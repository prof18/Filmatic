package com.prof18.filmatic.core.net

import com.prof18.filmatic.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url()
            .newBuilder()
                // TODO: I should add here also the lang query params
            .addQueryParameter("api_key", BuildConfig.TMDB_KEY)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}