@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    id("com.prof18.filmatic.android.library")
}

android {
    kotlin.sourceSets.configureEach {
        languageSettings.optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
    }
    namespace = "com.prof18.filmatic.libraries.testshared"
}

dependencies {
    implementation(libs.com.airbnb.android.lottie)
    implementation(libs.io.coil)
    implementation(libs.hilt.android)
    implementation(libs.hilt.android.testing)
    implementation(libs.androidx.test.rules)
    implementation(libs.com.squareup.okhttp3.mockwebserver)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.androidx.test.espresso.idling.resource)
}
