pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "spring-webflux"

include(
    "lib",
    "data:r2dbc"
)
