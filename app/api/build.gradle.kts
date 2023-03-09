dependencies {
    implementation(project(":lib"))
    implementation(project(":domain"))
    implementation(project(":service"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation("org.springframework.boot:spring-boot-starter-validation")
    compileOnly("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springframework:spring-context")
    implementation("org.springframework.data:spring-data-commons")

    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.0.2")

    runtimeOnly("io.netty:netty-all")
}
