package com.yunho.webfluxsample.repository

import com.yunho.webfluxsample.entity.TodoEntity
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.lang.RuntimeException

@Repository
class TodoRepository {
    private val todos = mutableListOf(TodoEntity(1, "hello"), TodoEntity(2, "world"))

    fun findById(id: Int): Mono<TodoEntity> {
        return Mono.just(todos.first { it.id == id })
            .switchIfEmpty { Mono.error(RuntimeException("no id!")) }
    }

    fun getTodos(): Mono<List<TodoEntity>> {
        return Mono.just(todos.toList())
    }
}