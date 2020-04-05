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

@file:Suppress("MayBeConstant")

object Versions {

    val gradle = "4.1.0-alpha04"
    val kotlin = "1.3.70"


    val appcompat = "1.2.0-alpha03"
    val design = "1.2.0-alpha05"
    val cardview = "1.0.0"
    val recyclerview = "1.0.0"
    val gson = "2.8.6"


    val androidxArch = "2.0.0"
    val constraintLayout = "2.0.0-beta4"

    val ktx = "1.3.0-alpha02"

    val coroutines = "1.3.5"
    val dagger = "2.27"
    val room = "2.1.0-rc01"

    val timber = "4.7.1"
    val retrofit = "2.7.2"
    val loggingInterceptor = "4.4.1"
    val glide = "4.11.0"
    val moshi = "1.9.2"
    val lifecycle = "2.3.0-alpha01"
    val lifecylceExtensions = "2.2.0"
    val leakCanary = "2.0-alpha-1"
    val crashlytics = "2.9.9"

    val junit = "4.13"
    val assertjCore = "3.12.2"
    val mockitoKotlin = "2.1.0"
    val mockitoInline = "2.27.0"

    val navigation = "2.3.0-alpha04"

    val lottie = "3.4.0"


    val gradleVersion = "0.28.0"


}

object Deps {

    // Gradle
    val gradleTools = "com.android.tools.build:gradle:${Versions.gradle}"

    // Kotlin
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val kotlinAndroid = "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"

    // Support libs
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val design = "com.google.android.material:material:${Versions.design}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"

    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecylceExtensions}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    val lifecycleKtx =  "androidx.lifecycle.lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    val leakCanaryAndroid = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"

    val gsonRetrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"

    // Dagger
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"


    // Coroutines
    val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // Navigation
    val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.navigation}"
    val navigation = "androidx.navigation:navigation-ui:${Versions.navigation}"
    val navigationKtx =  "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    val lottie = "com.airbnb.android:lottie:${Versions.lottie}"

    val gradleVersion = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersion}"


}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
}