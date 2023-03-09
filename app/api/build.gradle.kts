dependencies {
    implementation(project(":lib"))
    implementation(project(":domain"))
    implementation(project(":service"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation("org.springframework.boot:spring-boot-starter-validation")
    compileOnly("org.springframework.boot:spring-boot-configuration-processor")

    val springdocVersion = "1.6.12"
    implementation("org.springdoc:springdoc-openapi-webflux-ui:$springdocVersion")
    implementation("org.springdoc:springdoc-openapi-kotlin:$springdocVersion")

    implementation("org.springframework:spring-context")
    implementation("org.springframework.data:spring-data-commons")


    runtimeOnly("io.netty:netty-all")
}
