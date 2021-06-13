plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":libraries:ui-components"))

    applyCommonFeatureDeps()

    implementation(Deps.ABOUT_LIB_CORE)
    implementation(Deps.ABOUT_LIB)
}
