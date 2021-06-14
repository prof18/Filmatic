import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}")
        classpath("com.mikepenz.aboutlibraries.plugin:aboutlibraries-plugin:${Versions.ABOUT_LIB}")
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version BuildPluginsVersion.DETEKT
    id("org.jlleitschuh.gradle.ktlint") version BuildPluginsVersion.KTLINT
    id("com.github.ben-manes.versions") version BuildPluginsVersion.VERSIONS_PLUGIN
}

allprojects {
    repositories {
        google()
        mavenCentral()
        // Required by detekt
        jcenter {
            content {
                includeModule("org.jetbrains.kotlinx", "kotlinx-html-jvm")
            }
        }
    }
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
        plugin("com.github.ben-manes.versions")
    }

    ktlint {
        debug.set(false)
        version.set(Versions.KTLINT)
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
    compileSdkVersion(SDKConfig.COMPILE_SDK_VERSION)
    buildToolsVersion = SDKConfig.BUILD_TOOLS_VERSION

    defaultConfig {
        minSdk = SDKConfig.MIN_SDK_VERSION
        targetSdk = SDKConfig.TARGET_SDK_VERSION
        versionCode = Release.VERSION_CODE
        versionName = Release.VERSION_NAME

        testInstrumentationRunner = "com.prof18.filmatic.libraries.testshared.CustomTestRunner"
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
