package com.prof18.filmatic.libraries.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : UserPreferences {

    // Internal cache to avoid accessing every time to shared prefs
    internal val cacheMap = mutableMapOf<String, Any>()

    override fun getUserPreferredLocale(): String? {
        // get cached value
        val cachedLocale = cachedValue(PrefsField.USER_LOCALE.name) as? String?
        return if (cachedLocale != null) {
            cachedLocale
        } else {
            val savedValue = sharedPreferences.getString(
                PrefsField.USER_LOCALE.name,
                null,
            )
            if (savedValue != null) {
                cacheValue(
                    key = PrefsField.USER_LOCALE.name,
                    value = savedValue,
                )
            }
            savedValue
        }
    }

    override fun saveUserPreferredLocale(locale: String) {
        sharedPreferences.edit { putString(PrefsField.USER_LOCALE.name, locale) }
        cacheMap[PrefsField.USER_LOCALE.name] = locale
    }

    /**
     * Utils methods
     */
    private fun cachedValue(key: String): Any? = cacheMap[key]

    private fun cacheValue(key: String, value: Any) {
        cacheMap[key] = value
    }
}
