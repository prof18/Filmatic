plugins {
    id("com.android.library")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":libraries:preferences"))

    implementation(Deps.MOSHI)
    implementation(Deps.MOSHI_CONVERTER)
    implementation(Deps.RETROFIT)
    implementation(Deps.OKHTTP)
    implementation(Deps.OKHTTP_LOGGING_INTERCEPTOR)
    implementation(Deps.COROUTINES_CORE)
    implementation(Deps.COROUTINES_ANDROID)
    implementation(Deps.CORE_KTX)

    implementation(Deps.COIL)

    implementation(Deps.HILT)
    kapt(Deps.HILT_COMPILER)
}
