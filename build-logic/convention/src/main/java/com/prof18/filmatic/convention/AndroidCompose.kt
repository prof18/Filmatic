package com.prof18.filmatic.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        composeOptions.kotlinCompilerExtensionVersion = project.COMPOSE_COMPILER
        buildFeatures.compose = true

        dependencies {
            add("implementation", platform(COMPOSE_BOM))
            add("implementation", project.COMPOSE_BUNDLE)

            add("androidTestImplementation", platform(COMPOSE_BOM))
            add("androidTestImplementation", project.COMPOSE_TESTING_BUNDLE)

            add("debugImplementation", project.COMPOSE_TESTING_MANIFEST)
        }
    }
}
