@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    id("com.prof18.filmatic.android.application")
    id("com.prof18.filmatic.android.hilt")
    alias(libs.plugins.com.mikepenz.aboutlibraries)
}

android {
    namespace = "com.prof18.filmatic"

    defaultConfig {
        applicationId = "com.prof18.filmatic"
        versionCode = 10000
        versionName = "1.0.0"
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":features:home:contract"))
    implementation(project(":features:home:implementation"))
    implementation(project(":features:about:contract"))
    implementation(project(":features:about:implementation"))
    implementation(project(":libraries:ui-components"))
    implementation(project(":libraries:preferences"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.com.google.android.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.com.jakewharton.timber)
    implementation(libs.io.coil)

    debugImplementation(libs.com.squareup.leakcanary.android)
}


aboutLibraries {
    // configPath = "$rootDir/license-config"
}