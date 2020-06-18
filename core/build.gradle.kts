/*
 * Copyright 2019 Marco Gomiero
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

plugins {
    id("com.android.library" )
    id("kotlin-android" )
    id("kotlin-android-extensions" )
    id("kotlin-kapt" )
}

android {
    applyAndroidConfig()

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}

dependencies {
    implementation(project(":libraries:preferences"))

    implementation(Deps.dagger)
    kapt(Deps.daggerCompiler)

    implementation(Deps.lifecycleExtensions)
    implementation(Deps.moshi)
    implementation(Deps.moshiConverter)
    implementation(Deps.retrofit)
    implementation(Deps.loggingInterceptor)
    implementation(Deps.coroutinesCore)
    implementation(Deps.coroutinesAndroid)
    implementation(Deps.ktx)
    implementation(Deps.navigationKtx)
    implementation(Deps.navigationFragmentKtx)

}
