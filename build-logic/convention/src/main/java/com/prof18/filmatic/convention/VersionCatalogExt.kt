package com.prof18.filmatic.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType

// https://github.com/gradle/gradle/issues/19813
internal fun Project.version(key: String): String = extensions
    .getByType<VersionCatalogsExtension>()
    .named("libs")
    .findVersion(key)
    .get()
    .requiredVersion

internal fun Project.bundle(key: String): Provider<ExternalModuleDependencyBundle> = extensions
    .getByType<VersionCatalogsExtension>()
    .named("libs")
    .findBundle(key)
    .get()

internal fun Project.library(key: String): Provider<MinimalExternalModuleDependency> = extensions
    .getByType<VersionCatalogsExtension>()
    .named("libs")
    .findLibrary(key)
    .get()

internal fun Project.versionInt(key: String) = version(key).toInt()

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal val Project.ANDROID_COMPILE_SDK_VERSION get() = versionInt("android.compile.sdk")
internal val Project.ANDROID_MIN_SDK_VERSION get() = versionInt("android.min.sdk")
internal val Project.ANDROID_TARGET_SDK_VERSION get() = versionInt("android.target.sdk")
internal val Project.JVM_TARGET get() = version("jvm.target")
internal val Project.COMPOSE_COMPILER get() = version("androidx.compose.compiler")
internal val Project.COMPOSE_BUNDLE get() = bundle("compose")
internal val Project.COMPOSE_BOM get() = library("androidx.compose.bom")
internal val Project.COMPOSE_TESTING_BUNDLE get() = bundle("compose.testing")
internal val Project.COMPOSE_TESTING_MANIFEST get() = library("androidx.compose.ui.test.manifest")
