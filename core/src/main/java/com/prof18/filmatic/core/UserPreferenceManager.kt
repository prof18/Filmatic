package com.prof18.filmatic.core

import android.content.SharedPreferences
import androidx.core.content.edit
import com.prof18.filmatic.core.preference.PrefsField
import com.prof18.filmatic.core.preference.Preferences
import java.util.*

class UserPreferenceManager(private val sharedPreferences: SharedPreferences) {

    private val cacheMap = mutableMapOf<String, Any>()

    fun getUserPreferredLocale(): String {
        // get cached value
        val cachedLocale = cachedValue(PrefsField.USER_LOCALE.name) as String?
        return if (cachedLocale == null) {
            val savedValue = sharedPreferences.getString(Preferences.USER_PREFERENCES, null)
            if (savedValue == null) {
                val locale = Locale.getDefault().toString().replace("_", "-")
                saveUserPreferredLocale(locale)
                cacheValue(PrefsField.USER_LOCALE.name, locale)
                locale
            } else {
                cacheValue(PrefsField.USER_LOCALE.name, savedValue)
                savedValue
            }
        } else {
            cachedLocale
        }
    }

    fun saveUserPreferredLocale(locale: String) {
        sharedPreferences.edit { putString(PrefsField.USER_LOCALE.name, locale) }
        cacheMap[PrefsField.USER_LOCALE.name] = locale
    }

    /**
     * Utils methods
     */
    private fun cachedValue(key: String) = cacheMap[key]

    private fun cacheValue(key: String, value: Any) {
        cacheMap[key] = value
    }
}