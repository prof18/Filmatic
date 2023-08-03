import com.android.build.gradle.LibraryExtension
import com.prof18.filmatic.convention.ANDROID_TARGET_SDK_VERSION
import com.prof18.filmatic.convention.configureKotlinAndroid
import com.prof18.filmatic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidBaseLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = ANDROID_TARGET_SDK_VERSION
            }

            dependencies {
                add("testImplementation", kotlin("test"))
                add("testImplementation", libs.findLibrary("junit").get())
            }
        }
    }
}
