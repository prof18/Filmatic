/*
 * Copyright 2019 Marco Gomiero
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    applyAndroidConfig()
    defaultConfig {
        testInstrumentationRunner = "com.prof18.filmatic.features.home.FilmaticTestRunner"
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            freeCompilerArgs = listOf("-Xjvm-default=compatibility")
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    applyCommonDeps()
    applyTests()

    implementation(project(":core"))
    implementation(project(":libraries:uicomponents"))
    implementation(project(":libraries:preferences"))

    implementation(Deps.retrofit)
    implementation(Deps.moshi)
    implementation(Deps.moshiConverter)
    implementation(Deps.navigation)
    implementation(Deps.navigationKtx)
    implementation(Deps.navigationFragment)
    implementation(Deps.navigationFragmentKtx)
    implementation(Deps.lottie)
    implementation(Deps.coil)

    // Kapt
    kapt(Deps.lifecycleCompiler)
    kapt(Deps.moshiCodeGen)

    testImplementation(project(":libraries:testshared"))
    androidTestImplementation(project(":libraries:testshared"))

    androidTestImplementation(Deps.hiltTesting)
    kaptAndroidTest(Deps.hiltCompiler)
    kaptAndroidTest(Deps.hiltAndroidXCompiler)
}
