import org.gradle.api.artifacts.dsl.DependencyHandler

object BuildPluginsVersion {
    const val DETEKT = "1.17.1"
    const val KTLINT = "10.1.0"
    const val VERSIONS_PLUGIN = "0.38.0"
}

object Versions {
    const val APP_COMPAT = "1.2.0"
    const val MATERIAL = "1.3.0"
    const val RECYCLER_VIEW = "1.2.1"
    const val CONSTRAINT_LAYOUT = "2.0.4"
    const val CORE_KTX = "1.5.0"

    const val ACTIVITY_KTX = "1.3.0-beta01"
    const val LIFECYCLE = "2.4.0-alpha01"

    const val HILT = "2.37"

    const val COROUTINES = "1.5.0"
    const val RETROFIT = "2.9.0"
    const val OKHTTP = "4.9.1"
    const val MOSHI = "1.12.0"

    const val TIMBER = "4.7.1"
    const val LEAK_CANARY = "2.7"
    const val KTLINT = "0.41.0"

    const val NAVIGATION = "2.3.5"

    const val LOTTIE = "3.7.0"
    const val COIL = "1.2.2"
    const val ABOUT_LIB = "8.9.0"

    // Testing
    const val FAKER = "1.0.2"
    const val JUNIT = "4.13.2"
    const val TURBINE = "0.5.2"
    const val MOCK_WEB_SERVER = "4.9.1"
    const val MOCKITO_KOTLIN = "3.2.0"
    const val MOCKITO = "3.11.0"
    const val UI_AUTOMATOR = "2.1.3"
    const val TEST_ORCHESTRATOR = "1.3.0"

    const val ANDROIDX_TEST_EXT = "1.1.2"
    const val ANDROIDX_TEST = "1.3.0"
    const val ESPRESSO_CORE = "3.3.0"
    const val ESPRESSO_CONTRIB = "3.1.0"
}

object Deps {

    // Support libs
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${Versions.RECYCLER_VIEW}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"

    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"

    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"

    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val MOSHI_CONVERTER = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}"
    const val MOSHI = "com.squareup.moshi:moshi:${Versions.MOSHI}"
    const val MOSHI_CODE_GEN = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.MOSHI}"

    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"

    const val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:${Versions.LEAK_CANARY}"

    // Coroutines
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"

    const val LOTTIE = "com.airbnb.android:lottie:${Versions.LOTTIE}"
    const val COIL = "io.coil-kt:coil:${Versions.COIL}"

    // Hilt
    const val HILT = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"

    // Navigation
    const val NAVIGATION_FRAGMENT_KTX =
        "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"

    const val ABOUT_LIB_CORE = "com.mikepenz:aboutlibraries-core:${Versions.ABOUT_LIB}"
    const val ABOUT_LIB = "com.mikepenz:aboutlibraries:${Versions.ABOUT_LIB}"
}

object TestingDeps {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val COROUTINE_TEST =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES}"
    const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:${Versions.OKHTTP}"
    const val TURBINE = "app.cash.turbine:turbine:${Versions.TURBINE}"
    const val HILT = "com.google.dagger:hilt-android-testing:${Versions.HILT}"
    const val MOCKITO_KOTLIN = "org.mockito.kotlin:mockito-kotlin:${Versions.MOCKITO_KOTLIN}"
    const val MOCKITO_CORE = "org.mockito:mockito-core:${Versions.MOCKITO}"
    const val FAKER = "com.github.javafaker:javafaker:${Versions.FAKER}"
}

object AndroidTestingDeps {
    const val ANDROIDX_TEST_RULES = "androidx.test:rules:${Versions.ANDROIDX_TEST}"
    const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_TEST_EXT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    const val ESPRESSO_CONTRIB =
        "androidx.test.espresso:espresso-contrib:${Versions.ESPRESSO_CONTRIB}"
    const val HILT = "com.google.dagger:hilt-android-testing:${Versions.HILT}"
    const val OKHTTP_IDLING_RESOURCE = "com.jakewharton.espresso:okhttp3-idling-resource:1.0.0"
    const val NAVIGATION = "androidx.navigation:navigation-testing:${Versions.NAVIGATION}"
}

fun DependencyHandler.applyCommonFeatureDeps() {
    add("implementation", Deps.APP_COMPAT)
    add("implementation", Deps.CORE_KTX)
    add("implementation", Deps.MATERIAL)
    add("implementation", Deps.CONSTRAINT_LAYOUT)
    add("implementation", Deps.LIFECYCLE_RUNTIME)
    add("implementation", Deps.TIMBER)
}
