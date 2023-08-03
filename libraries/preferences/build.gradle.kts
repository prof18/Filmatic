@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    id("com.prof18.filmatic.android.library")
    id("com.prof18.filmatic.android.hilt")
}

android {
    namespace = "com.prof18.filmatic.libraries.preferences"
}

dependencies {
    implementation(libs.androidx.core.ktx)

    testImplementation(libs.junit)
    testImplementation(libs.org.mockito.kotlin)
    testImplementation(libs.org.mockito.core)
}
