package me.lavinoys.app.api.handler

import me.lavinoys.domain.dto.CreateBoardRequest
import me.lavinoys.service.BoardService
import org.springframework.stereotype.Controller
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Controller
class BoardHandler(
    val service: BoardService
) {
    suspend fun create(request: ServerRequest): ServerResponse {
        val param = request.awaitBody<CreateBoardRequest>()
        return ServerResponse.ok().bodyValueAndAwait(service.create(param))
    }
}
