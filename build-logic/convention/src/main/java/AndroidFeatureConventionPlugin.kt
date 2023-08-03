import com.android.build.gradle.LibraryExtension
import com.prof18.filmatic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.prof18.filmatic.android.library")
                apply("com.prof18.filmatic.android.hilt")
            }

            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "com.prof18.filmatic.libraries.testshared.CustomTestRunner"
                }

                buildFeatures {
                    viewBinding = true
                }
            }

            dependencies {
                add("implementation", project(":core"))

                add("testImplementation", kotlin("test"))
                add("testImplementation", libs.findLibrary("junit").get())
            }
        }
    }
}
