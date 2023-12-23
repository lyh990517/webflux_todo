package com.yunho.webfluxsample.service

import com.yunho.webfluxsample.entity.Todo
import com.yunho.webfluxsample.repository.TodoRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class TodoService(private val repository: TodoRepository) {

    fun getTodoById(id: Int): Mono<Todo> {
        return repository.findById(id).map { entity -> Todo(entity.id, entity.content) }
    }

    fun getTodos(): Mono<List<Todo>> {
        return repository.getTodos()
            .map { list ->
                list.map { entity ->
                    Todo(entity.id, entity.content)
                }
            }
    }
}