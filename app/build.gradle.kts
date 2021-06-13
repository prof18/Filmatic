plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.mikepenz.aboutlibraries.plugin")
}

android {
    defaultConfig {
        applicationId = SDKConfig.APP_ID
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":features:home"))
    implementation(project(":features:about"))
    implementation(project(":libraries:ui-components"))
    implementation(project(":libraries:navigation"))
    implementation(project(":libraries:preferences"))

    implementation(Deps.CORE_KTX)
    implementation(Deps.APP_COMPAT)
    implementation(Deps.MATERIAL)
    implementation(Deps.CONSTRAINT_LAYOUT)
    implementation(Deps.TIMBER)
    implementation(Deps.COIL)

    implementation(Deps.HILT)
    kapt(Deps.HILT_COMPILER)

    debugImplementation(Deps.LEAK_CANARY)
}
