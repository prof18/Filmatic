plugins {
    `kotlin-dsl`
}

group = "com.prof18.filmatic.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile::class.java).configureEach {
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    compileOnly(libs.com.android.tools.build.gradle)
    compileOnly(libs.org.jetbrains.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.prof18.filmatic.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "com.prof18.filmatic.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "com.prof18.filmatic.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "com.prof18.filmatic.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "com.prof18.filmatic.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidBaseLibrary") {
            id = "com.prof18.filmatic.android.base.library"
            implementationClass = "AndroidBaseLibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "com.prof18.filmatic.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
    }
}
