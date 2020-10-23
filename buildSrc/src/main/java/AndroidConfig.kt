/*
 * Copyright 2020 Marco Gomiero
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

// For app module
fun BaseAppModuleExtension.applyAndroidConfig() {
    compileSdkVersion(Config.compileSdk)
    defaultConfig {
        minSdkVersion(Config.minSdk)
        targetSdkVersion(Config.targetSdk)
        versionCode = Release.versionCode
        versionName = Release.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }
    buildToolsVersion = Config.buildTools

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles( getDefaultProguardFile("proguard-android.txt" ), "proguard-rules.pro" )
            buildConfigField("String", "TMDB_KEY", "\"${Key.tmdbApiKey}\"")

        }
        getByName("debug") {
            buildConfigField("String", "TMDB_KEY", "\"${Key.tmdbApiKey}\"")
        }
    }
    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }

    packagingOptions {
        exclude("win32-x86-64/attach_hotspot_windows.dll" )
        exclude("win32-x86/attach_hotspot_windows.dll" )
        exclude("META-INF/AL2.0" )
        exclude("META-INF/LGPL2.1" )
        exclude("META-INF/licenses/ASM" )
    }
}

// For library modules
fun LibraryExtension.applyAndroidConfig() {
    compileSdkVersion(Config.compileSdk)
    defaultConfig {
        minSdkVersion(Config.minSdk)
        targetSdkVersion(Config.targetSdk)
        versionCode = Release.versionCode
        versionName = Release.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }
    buildToolsVersion = Config.buildTools

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles( getDefaultProguardFile("proguard-android.txt" ), "proguard-rules.pro" )
            buildConfigField("String", "TMDB_KEY", "\"${Key.tmdbApiKey}\"")

        }
        getByName("debug") {
            buildConfigField("String", "TMDB_KEY", "\"${Key.tmdbApiKey}\"")
        }
    }
    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }


    packagingOptions {
        exclude("win32-x86-64/attach_hotspot_windows.dll" )
        exclude("win32-x86/attach_hotspot_windows.dll" )
        exclude("META-INF/AL2.0" )
        exclude("META-INF/LGPL2.1" )
        exclude("META-INF/licenses/ASM" )
    }

}

