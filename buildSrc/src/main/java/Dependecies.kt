@file:Suppress("MayBeConstant")
import org.gradle.api.JavaVersion

object Modules {
    val app = ":app"
    val core = ":core"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Config {
    val minSdk = 21
    val compileSdk = 28
    val targetSdk = 28
    val javaVersion = JavaVersion.VERSION_1_8
    val buildTools = "28.0.3"
}

object Versions {
    val gradle = "3.4.0"

    val compileSdk = 28
    val minSdk = 21
    val targetSdk = 28

    val appcompat = "1.0.2"
    val design = "1.0.0"
    val cardview = "1.0.0"
    val recyclerview = "1.0.0"
    val gson = "2.8.5"


    val androidxArch = "2.0.0"
    val constraintLayout = "1.1.3"

    val ktx = "1.0.1"

    val coroutines = "1.1.1"
    val dagger = "2.16"
    val room = "2.1.0-rc01"

    val kotlin = "1.3.40"
    val timber = "4.7.1"
    val retrofit = "2.6.0"
    val loggingInterceptor = "3.12.1"
    val glide = "4.9.0"
    val moshi = "1.8.0"
    val lifecycle = "2.1.0-beta01"
    val leakCanary = "2.0-alpha-1"
    val crashlytics = "2.9.9"

    val junit = "4.12"
    val assertjCore = "3.12.2"
    val mockitoKotlin = "2.1.0"
    val mockitoInline = "2.27.0"
}

object Deps {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    // Support libs
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val design = "com.google.android.material:material:${Versions.design}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val rxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"

    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
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


}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
}