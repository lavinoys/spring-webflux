pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "spring-webflux"

include(
    "lib",
    "domain",
    "data:db-board",
    "service",
    "app",

    "app:api"
)
