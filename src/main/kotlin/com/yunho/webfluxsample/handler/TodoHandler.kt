package com.yunho.webfluxsample.handler

import com.yunho.webfluxsample.handler.dto.TodoResponse
import com.yunho.webfluxsample.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

@Component
class TodoHandler(private val todoService: TodoService) {

    fun getTodoById(serverRequest: ServerRequest) : Mono<ServerResponse> {
        val todoId = serverRequest.pathVariables()["id"]?.toInt() ?: 1

        return todoService.getTodoById(todoId).map {
            TodoResponse(it.id,it.content)
        }.flatMap { response ->
            ServerResponse.ok().bodyValue(response)
        }.onErrorMap {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }
}