import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.kapt).apply(false)
    alias(libs.plugins.hilt.gradle).apply(false)
    alias(libs.plugins.ksp).apply(false)
    alias(libs.plugins.androidx.navigation.safe.args).apply(false)
    alias(libs.plugins.com.mikepenz.aboutlibraries).apply(false)
    alias(libs.plugins.com.github.ben.manes.versions)
    alias(libs.plugins.io.gitlab.arturbosch.detekt)
    alias(libs.plugins.org.jlleitschuh.gradle.ktlint)
}

subprojects {
    // Apply common configurations to every android app or android library module
    afterEvaluate {
        (
            extensions.findByType(com.android.build.gradle.LibraryExtension::class)
                ?: extensions.findByType(com.android.build.gradle.AppExtension::class)
            )?.apply {
            applyAndroidCommons()
        }
    }

    apply {
        plugin("io.gitlab.arturbosch.detekt")
        plugin("org.jlleitschuh.gradle.ktlint")
    }

    ktlint {
        debug.set(false)
        version.set("0.41.0")
        verbose.set(true)
        android.set(false)
        outputToConsole.set(true)
        ignoreFailures.set(false)
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }

    detekt {
        config = rootProject.files("config/detekt/detekt.yml")
        reports {
            html {
                enabled = true
                destination = file("build/reports/detekt.html")
            }
        }
    }
}

fun PluginContainer.configure(project: Project) {
    whenPluginAdded {
        if (this is BasePlugin) {
            project.extensions
                .getByType<com.android.build.gradle.LibraryExtension>()
                .apply {
                    applyAndroidCommons()
                }
        }
    }
}

fun com.android.build.gradle.BaseExtension.applyAndroidCommons() {
    compileSdkVersion(libs.versions.android.compile.sdk.get().toInt())
    defaultConfig {
        minSdk = libs.versions.android.min.sdk.get().toInt()
        targetSdk = libs.versions.android.target.sdk.get().toInt()
        versionCode = 10000
        versionName = "1.0.0"

        testInstrumentationRunner = "com.prof18.filmatic.libraries.testshared.CustomTestRunner"
    }

    testOptions {
        // execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
}

val String.byProperty: String?
    get() {
        val local = java.util.Properties()
        val localProperties: File = rootProject.file("local.properties")
        if (localProperties.exists()) {
            localProperties.inputStream().use { local.load(it) }
            return local.getProperty(this)
        }
        return null
    }

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }

    withType<DependencyUpdatesTask> {
        rejectVersionIf {
            candidate.version.isStableVersion().not()
        }
    }
}

fun String.isStableVersion(): Boolean {
    val stableKeyword =
        listOf("RELEASE", "FINAL", "GA").any { toUpperCase(java.util.Locale.ROOT).contains(it) }
    return stableKeyword || Regex("^[0-9,.v-]+(-r)?$").matches(this)
}
