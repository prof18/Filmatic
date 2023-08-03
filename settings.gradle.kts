pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "filmatic"

include(":app")
include(":core")

include(":features:home:implementation")
include(":features:home:contract")
include(":features:about:implementation")
include(":features:about:contract")

include(":libraries:preferences")
include(":libraries:ui-components")
include(":libraries:test-shared")
include(":libraries:error")
