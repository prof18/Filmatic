@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    id("com.prof18.filmatic.android.feature")
}

android {
    namespace = "com.prof18.filmatic.features.about"
}

dependencies {
    implementation(project(":features:about:contract"))
    implementation(project(":libraries:ui-components"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.com.google.android.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.com.jakewharton.timber)
    implementation(libs.com.mikepenz.aboutlibraries.core)
    implementation(libs.com.mikepenz.aboutlibraries)
}
