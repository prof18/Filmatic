package com.prof18.filmatic.libraries.preferences

interface UserPreferences {
    fun getUserPreferredLocale(): String?
    fun saveUserPreferredLocale(locale: String)
}
