import org.gradle.api.artifacts.dsl.DependencyHandler

object Versions {
    const val APP_COMPAT = "1.2.0"
    const val MATERIAL = "1.3.0"
    const val CONSTRAINT_LAYOUT = "2.0.4"
    const val CORE_KTX = "1.5.0"
    const val LIFECYCLE = "2.5.1"
    const val TIMBER = "4.7.1"
    const val KTLINT = "0.41.0"
    const val NAVIGATION = "2.5.0"
    const val ANDROIDX_TEST = "1.3.0"
    const val ESPRESSO_CORE = "3.3.0"
    const val ESPRESSO_CONTRIB = "3.1.0"
}

object Deps {
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
}

fun DependencyHandler.applyCommonFeatureDeps() {
    add("implementation", Deps.APP_COMPAT)
    add("implementation", Deps.CORE_KTX)
    add("implementation", Deps.MATERIAL)
    add("implementation", Deps.CONSTRAINT_LAYOUT)
    add("implementation", Deps.LIFECYCLE_RUNTIME)
    add("implementation", Deps.TIMBER)
}
