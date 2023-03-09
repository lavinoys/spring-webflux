dependencies {
    implementation(project(":lib"))
    implementation(project(":domain"))
    implementation(project(":data:db-board"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework.data:spring-data-commons")
    implementation("org.springframework:spring-tx")
}
