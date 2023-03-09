package me.lavinoys.app.api

import me.lavinoys.app.api.doc.BoardApiDoc
import me.lavinoys.app.api.handler.BoardHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter
import java.net.URI

@Configuration
class ApiRouter {
    @Bean
    fun indexRouter() = coRouter {
        GET("/") { temporaryRedirect(URI("/swagger-ui.html")).buildAndAwait() }
    }

    @Bean
    @BoardApiDoc
    fun BoardHandler.router() = coRouter {
        "/api/v1/boards".nest {
            POST("/", ::create)
        }
    }
}
