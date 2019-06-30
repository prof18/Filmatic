@file:Suppress("MayBeConstant")

import org.gradle.api.JavaVersion

object Config {
    val minSdk = 21
    val compileSdk = 28
    val targetSdk = 28
    val javaVersion = JavaVersion.VERSION_1_8
    val buildTools = "28.0.3"
}
