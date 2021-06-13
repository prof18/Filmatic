plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

kotlin.sourceSets.configureEach {
    languageSettings.useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
    languageSettings.useExperimentalAnnotation("coil.annotation.ExperimentalCoilApi")
}

dependencies {
    implementation(project(":core"))
    implementation(Deps.COIL)
    implementation(Deps.HILT)
    implementation(AndroidTestingDeps.HILT)
    implementation(AndroidTestingDeps.ANDROIDX_TEST_RULES)
    implementation(TestingDeps.MOCK_WEB_SERVER)
    implementation(TestingDeps.COROUTINE_TEST) {
        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-debug")
    }
}
