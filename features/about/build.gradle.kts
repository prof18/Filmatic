@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":libraries:ui-components"))

    applyCommonFeatureDeps()

    implementation(libs.com.mikepenz.aboutlibraries.core)
    implementation(libs.com.mikepenz.aboutlibraries)
}
