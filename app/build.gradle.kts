@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.gradle)
    alias(libs.plugins.com.mikepenz.aboutlibraries)
}

android {
    defaultConfig {
        applicationId = "com.prof18.filmatic"
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
    namespace = "com.prof18.filmatic"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":features:home"))
    implementation(project(":features:about"))
    implementation(project(":libraries:ui-components"))
    implementation(project(":libraries:navigation"))
    implementation(project(":libraries:preferences"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.com.google.android.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.com.jakewharton.timber)
    implementation(libs.io.coil)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    debugImplementation(libs.com.squareup.leakcanary.android)
}


aboutLibraries {
    // configPath = "$rootDir/license-config"
}