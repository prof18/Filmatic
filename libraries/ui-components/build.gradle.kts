@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    id("com.prof18.filmatic.android.library")
}

android {
    buildFeatures {
        viewBinding = true
    }
    namespace = "com.prof18.filmatic.libraries.uicomponents"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.com.google.android.material)
    implementation(libs.androidx.constraintlayout)
}
