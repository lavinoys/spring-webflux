package me.lavinoys.app.api.doc

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springdoc.core.annotations.RouterOperation
import org.springdoc.core.annotations.RouterOperations
import org.springframework.web.bind.annotation.RequestMethod

private const val TAG = "Board"
@RouterOperations(
    RouterOperation(
        path = "",
        method = [RequestMethod.POST],
        operation = Operation(
            tags = [TAG],
            operationId = "$TAG:props",
            summary = "",
            parameters = [
                Parameter()
            ],
            responses = [ApiResponse()]
        )
    )
)
annotation class BoardApiDoc()
