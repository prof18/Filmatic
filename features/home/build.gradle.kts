plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
}

android {
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

    // Shared folder for tests
    sourceSets {
        getByName("test") {
            java.setSrcDirs(java.srcDirs + "$projectDir/src/testShared/java")
        }

        getByName("androidTest") {
            java.setSrcDirs(java.srcDirs + "$projectDir/src/testShared/java")
        }
    }

    kotlin.sourceSets.configureEach {
        languageSettings.useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
        languageSettings.useExperimentalAnnotation("kotlin.time.ExperimentalTime")
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":libraries:ui-components"))
    implementation(project(":libraries:navigation"))
    implementation(project(":libraries:preferences"))

    applyCommonFeatureDeps()

    implementation(Deps.RECYCLER_VIEW)
    implementation(Deps.RETROFIT)
    implementation(Deps.MOSHI)
    implementation(Deps.MOSHI_CONVERTER)
    implementation(Deps.MOSHI_CODE_GEN)
    implementation(Deps.NAVIGATION_UI_KTX)
    implementation(Deps.NAVIGATION_FRAGMENT_KTX)
    implementation(Deps.LOTTIE)
    implementation(Deps.COIL)

    implementation(Deps.HILT)
    kapt(Deps.HILT_COMPILER)

    testImplementation(project(":libraries:test-shared"))
    testImplementation(TestingDeps.JUNIT)
    testImplementation(TestingDeps.COROUTINE_TEST) {
        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-debug")
    }
    testImplementation(TestingDeps.MOCK_WEB_SERVER)
    testImplementation(TestingDeps.TURBINE)
    testImplementation(TestingDeps.FAKER)

    androidTestImplementation(project(":libraries:test-shared"))
    androidTestImplementation(TestingDeps.MOCK_WEB_SERVER)
    androidTestImplementation(TestingDeps.FAKER)
    androidTestImplementation(AndroidTestingDeps.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(AndroidTestingDeps.ESPRESSO_CORE)
    androidTestImplementation(AndroidTestingDeps.ESPRESSO_CONTRIB)
    androidTestImplementation(AndroidTestingDeps.ANDROIDX_TEST_RULES)
    androidTestImplementation(AndroidTestingDeps.HILT)
    androidTestImplementation(TestingDeps.COROUTINE_TEST) {
        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-debug")
    }
    androidTestImplementation(AndroidTestingDeps.OKHTTP_IDLING_RESOURCE)
    kaptAndroidTest(Deps.HILT_COMPILER)
}
