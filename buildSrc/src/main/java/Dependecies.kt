object ApplicationId {
    val id = "com.prof18.filmatic"
}

object Modules {
    val app = ":app"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
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

    val ktx = "1.0.0-alpha1"

    val kotlin = "1.3.31"
    val timber = "4.7.1"
    val retrofit = "2.5.0"
    val loggingInterceptor = "3.12.1"
    val glide = "4.9.0"
    val moshi = "1.8.0"
    val lifecycle = "2.0.0"
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

    val leakCanaryAndroid = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"

}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
}