@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.gradle)
    alias(libs.plugins.androidx.navigation.safe.args)
    alias(libs.plugins.ksp)
}

android {
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }

    // Shared folder for
    sourceSets {
        getByName("test") {
            java.srcDir("$projectDir/src/testShared/java")
        }

        getByName("androidTest") {
            java.srcDir("$projectDir/src/testShared/java")
        }
    }

    kotlin.sourceSets.configureEach {
        languageSettings.optIn("kotlin.time.ExperimentalTime")
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":libraries:ui-components"))
    implementation(project(":libraries:navigation"))
    implementation(project(":libraries:preferences"))

    applyCommonFeatureDeps()

    implementation(libs.androidx.recyclerview)
    implementation(libs.com.squareup.retrofit)
    implementation(libs.com.squareup.retrofit.converter.moshi)
    implementation(libs.com.squareup.moshi)
    ksp(libs.com.squareup.moshi.kotlin.codegen)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.com.airbnb.android.lottie)
    implementation(libs.io.coil)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(project(":libraries:test-shared"))
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.com.squareup.okhttp3.mockwebserver)
    testImplementation(libs.app.cash.turbine)
    testImplementation(libs.com.github.javafaker)

    androidTestImplementation(project(":libraries:test-shared"))
    androidTestImplementation(libs.com.squareup.okhttp3.mockwebserver)
    androidTestImplementation(libs.com.github.javafaker)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.espresso.contrib)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.hilt.android)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.com.jakewharton.espresso.okhttp3.idling.resource)
    kaptAndroidTest(libs.hilt.compiler)
}
