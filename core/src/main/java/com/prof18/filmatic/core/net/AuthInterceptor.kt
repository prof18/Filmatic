package com.prof18.filmatic.core.net

import com.m2f.archer.crud.GetRepository
import com.m2f.archer.crud.PutRepository
import com.m2f.archer.crud.get
import com.m2f.archer.crud.put
import com.prof18.filmatic.core.BuildConfig
import com.prof18.filmatic.core.architecture.PrefsField
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val getUserRepository: @JvmSuppressWildcards GetRepository<PrefsField, String>,
    private val putUserRepository: @JvmSuppressWildcards PutRepository<PrefsField, String>,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var userPrefLocale = runBlocking {
            getUserRepository.get(PrefsField.USER_LOCALE).getOrNull()
        }
        // Move this saving into the onboarding flow
        if (userPrefLocale == null) {
            userPrefLocale = Locale.getDefault().toString().replace("_", "-")
            runBlocking {
                putUserRepository.put(PrefsField.USER_LOCALE, userPrefLocale)
            }
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
