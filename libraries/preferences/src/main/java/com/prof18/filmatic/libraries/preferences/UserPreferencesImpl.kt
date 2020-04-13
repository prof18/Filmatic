/*
 * Copyright 2020 Marco Gomiero
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

package com.prof18.filmatic.libraries.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import java.util.*
import javax.inject.Inject

class UserPreferencesImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    UserPreferences {

    private val cacheMap = mutableMapOf<String, Any>()

    override fun getUserPreferredLocale(): String {
        // get cached value
        val cachedLocale =
            cachedValue(PrefsField.USER_LOCALE.name) as String?
        return if (cachedLocale == null) {
            val savedValue = sharedPreferences.getString(
                Preferences.USER_PREFERENCES,
                null
            )
            if (savedValue == null) {
                val locale = Locale.getDefault().toString().replace("_", "-")
                saveUserPreferredLocale(locale)
                cacheValue(
                    PrefsField.USER_LOCALE.name,
                    locale
                )
                locale
            } else {
                cacheValue(
                    PrefsField.USER_LOCALE.name,
                    savedValue
                )
                savedValue
            }
        } else {
            cachedLocale
        }
    }

    override fun saveUserPreferredLocale(locale: String) {
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