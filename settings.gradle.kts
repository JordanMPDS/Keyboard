pluginManagement {
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
        maven { setUrl("https://www.jitpack.io") }
        mavenLocal()
    }
}
include(":app")

// Fossify Commons is built from the commons submodule, a fork without the checks against forks of the apps
includeBuild("commons") {
    dependencySubstitution {
        substitute(module("org.fossify:commons")).using(project(":commons"))
    }
}
