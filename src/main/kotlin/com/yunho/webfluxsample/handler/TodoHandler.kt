package com.yunho.webfluxsample.handler

import com.yunho.webfluxsample.entity.Todo
import com.yunho.webfluxsample.handler.dto.TodoListResponse
import com.yunho.webfluxsample.handler.dto.TodoResponse
import com.yunho.webfluxsample.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

@Component
class TodoHandler(private val todoService: TodoService) {

    fun getTodoById(serverRequest: ServerRequest): Mono<ServerResponse> {
        val todoId = serverRequest.pathVariables()["id"]?.toLong() ?: 1

        return todoService.getTodoById(todoId).map {
            TodoResponse(it.id, it.content)
        }.flatMap { response ->
            ServerResponse.ok().bodyValue(response)
        }.onErrorMap {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    fun getTodos(serverRequest: ServerRequest): Mono<ServerResponse> {
        return todoService.getTodos().collectList().flatMap {
            println("$it")
            ServerResponse.ok().bodyValue(TodoListResponse(it))
        }
    }

    fun createTodo(serverRequest: ServerRequest): Mono<ServerResponse> {
        return serverRequest
            .bodyToMono(Todo::class.java)
            .doOnNext {
                todoService.createTodos(it)
            }.flatMap {
                ServerResponse.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(it)
            }
    }

    fun deleteTodo(serverRequest: ServerRequest): Mono<ServerResponse> {
        val id = serverRequest.pathVariables()["id"]?.toLong() ?: 1

        todoService.deleteTodo(id)
        return ServerResponse.ok().bodyValue(id)
    }
}