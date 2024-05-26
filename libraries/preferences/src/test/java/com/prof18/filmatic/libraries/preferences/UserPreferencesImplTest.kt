package com.prof18.filmatic.libraries.preferences

import android.content.SharedPreferences
import com.prof18.filmatic.core.architecture.PrefsField
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class UserPreferencesImplTest {

    private val locale = "en-US"
    private lateinit var userPreferences: UserPreferencesImpl

    @Before
    fun setup() {

        val mockEdit: SharedPreferences.Editor = mock {
            on { putString(any(), any()) } doReturn this.mock
        }

        val sharedPrefs: SharedPreferences = mock {
            on { getString(PrefsField.USER_LOCALE.name, null) } doReturn locale
            on { edit() } doReturn mockEdit
        }
        userPreferences = UserPreferencesImpl(sharedPrefs)
    }

    @Test
    fun `getUserPreferredLocale returns data from cache`() {
        // Setup
        val italyLocale = "it-IT"
        userPreferences.cacheMap[PrefsField.USER_LOCALE.name] = italyLocale

        val userLocale = userPreferences.getUserPreferredLocale()
        assertEquals(italyLocale, userLocale)
    }

    @Test
    fun `getUserPreferredLocale persist on cache if value not already present`() {
        // Check for safe
        assertNull(userPreferences.cacheMap[PrefsField.USER_LOCALE.name])

        val userLocale = userPreferences.getUserPreferredLocale()
        assertEquals(locale, userLocale)
        assertEquals(userLocale, userPreferences.cacheMap[PrefsField.USER_LOCALE.name])
    }

    @Test
    fun `saveUserPreferredLocale saves value in cache`() {
        // Check for safe
        assertNull(userPreferences.cacheMap[PrefsField.USER_LOCALE.name])

        userPreferences.saveUserPreferredLocale(locale)
        assertEquals(locale, userPreferences.cacheMap[PrefsField.USER_LOCALE.name])
    }
}
