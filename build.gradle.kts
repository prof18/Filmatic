import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
plugins {
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
}

allprojects {
    apply {
        plugin("io.gitlab.arturbosch.detekt")
    }

    dependencies {
        detektPlugins(rootProject.libs.io.gitlab.arturbosch.detekt.formatting) {
            exclude(group = "org.slf4j", module = "slf4j-nop")
        }
    }

    detekt {
        config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
        source.setFrom(files("src/main/java", "src/test/java", "src/androidTest/java"))
        parallel = true
    }
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
        listOf("RELEASE", "FINAL", "GA").any { uppercase(java.util.Locale.ROOT).contains(it) }
    return stableKeyword || Regex("^[0-9,.v-]+(-r)?$").matches(this)
}
