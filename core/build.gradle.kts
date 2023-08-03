import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    id("com.prof18.filmatic.android.base.library")
    id("com.prof18.filmatic.android.hilt")
}

android {
    namespace = "com.prof18.filmatic.core"

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        getByName("release") {
            // Network API key
            buildConfigField(
                "String",
                "TMDB_KEY",
                "\"${"tmdbKey".byProperty ?: System.getenv("TMDB_KEY")}\""
            )
        }
        getByName("debug") {
            // Network API key
            buildConfigField(
                "String",
                "TMDB_KEY",
                "\"${"tmdbKey".byProperty ?: System.getenv("TMDB_KEY")}\""
            )
        }
    }
}

dependencies {
    implementation(libs.com.squareup.retrofit)
    implementation(libs.com.squareup.retrofit.converter.moshi)
    implementation(libs.com.squareup.moshi)
    implementation(libs.com.squareup.okhttp3.okhttp)
    implementation(libs.com.squareup.okhttp3.logging.interceptor)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.core.ktx)

    implementation(libs.io.coil)
}

val String.byProperty: String?
    get() {
        val local = Properties()
        val localProperties: File = rootProject.file("local.properties")
        if (localProperties.exists()) {
            localProperties.inputStream().use { local.load(it) }
            return local.getProperty(this)
        }
        return null
    }