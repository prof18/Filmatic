package com.prof18.filmatic.core.userprefs

interface UserPreferences {
    fun getUserPreferredLocale(): String?
    fun saveUserPreferredLocale(locale: String)
}
