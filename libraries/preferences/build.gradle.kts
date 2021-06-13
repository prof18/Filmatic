plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Deps.CORE_KTX)

    implementation(Deps.HILT)
    kapt(Deps.HILT_COMPILER)

    testImplementation(TestingDeps.JUNIT)
    testImplementation(TestingDeps.MOCKITO_CORE)
    testImplementation(TestingDeps.MOCKITO_KOTLIN)
}
