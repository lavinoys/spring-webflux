import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.3"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
    id("org.jetbrains.dokka") version "1.7.20"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("kapt") version "1.7.10"
}

configurations {
    runtimeClasspath {
        extendsFrom(
            developmentOnly.get(),
            configurations.annotationProcessor.get()
        )
    }
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }

    val javaVersion = JavaVersion.VERSION_17.toString()

    tasks.withType<JavaCompile> {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

subprojects {

    apply {
        plugin("kotlin")
        plugin("kotlin-spring")
        // for devtools
        plugin("kotlin-kapt")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.dokka")
    }

    val dokkaPlugin by configurations

    dependencies {
        implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("io.github.microutils:kotlin-logging:2.1.23")
        implementation("org.springframework.boot:spring-boot-starter")

        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        implementation("com.ninja-squad:springmockk:3.1.1")

        dokkaPlugin("org.jetbrains.dokka:versioning-plugin:1.6.0")
    }

    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }
}
