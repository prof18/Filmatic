@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.gradle)
}

android {
    kotlinOptions {
        jvmTarget = "11"
    }
    namespace = "com.prof18.filmatic.core"
}

dependencies {
    implementation(project(":libraries:preferences"))

    implementation(libs.com.squareup.retrofit)
    implementation(libs.com.squareup.retrofit.converter.moshi)
    implementation(libs.com.squareup.moshi)
    implementation(libs.com.squareup.okhttp3.okhttp)
    implementation(libs.com.squareup.okhttp3.logging.interceptor)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.core.ktx)

    implementation(libs.io.coil)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
