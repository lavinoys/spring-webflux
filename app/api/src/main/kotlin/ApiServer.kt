package me.lavinoys.app.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

fun main() {
    runApplication<ApiServer>()
}

@SpringBootApplication
class ApiServer
