package com.prof18.filmatic.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = ANDROID_COMPILE_SDK_VERSION

        defaultConfig {
            minSdk = ANDROID_MIN_SDK_VERSION
            testInstrumentationRunner = "com.prof18.filmatic.libraries.testshared.CustomTestRunner"
            // testInstrumentationRunnerArguments["clearPackageData"] = "true"
        }

        testOptions {
            // execution = "ANDROIDX_TEST_ORCHESTRATOR"
            animationsDisabled = true
            // unitTests {
            //     isReturnDefaultValues = true
            //     isIncludeAndroidResources = true
            // }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        kotlinOptions {
            jvmTarget = JVM_TARGET
        }

        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = "11"
            }
        }

        tasks.withType(org.jetbrains.kotlin.gradle.tasks.KaptGenerateStubs::class.java).configureEach {
            compilerOptions.jvmTarget.set(JvmTarget.JVM_11)
        }

        packagingOptions {
            resources.excludes.add("META-INF/*.kotlin_module")
        }
    }

    kotlinExtension.sourceSets.configureEach {
        languageSettings.optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
    }
}

fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}
