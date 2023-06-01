@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    kotlinOptions {
        jvmTarget = "11"
    }
    namespace = "com.prof18.filmatic.libraries.navigation"
}
