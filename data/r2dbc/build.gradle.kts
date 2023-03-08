dependencies {
    implementation(project(":lib"))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework.data:spring-data-commons")
    implementation("org.springframework:spring-tx")

    runtimeOnly("org.mariadb:r2dbc-mariadb:1.1.2")

    testRuntimeOnly("io.r2dbc:r2dbc-h2")
}
