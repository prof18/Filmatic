package com.prof18.filmatic.core.net

import com.prof18.filmatic.core.BuildConfig
import com.prof18.filmatic.core.userprefs.UserPreferences
import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val userPreferences: UserPreferences,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var userPrefLocale = userPreferences.getUserPreferredLocale()
        // Move this saving into the onboarding flow
        if (userPrefLocale == null) {
            userPrefLocale = Locale.getDefault().toString().replace("_", "-")
            userPreferences.saveUserPreferredLocale(userPrefLocale)
        }

        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("language", userPrefLocale)
            .addQueryParameter("api_key", BuildConfig.TMDB_KEY)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
