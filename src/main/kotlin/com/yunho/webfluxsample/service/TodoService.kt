package com.yunho.webfluxsample.service

import com.yunho.webfluxsample.entity.Todo
import com.yunho.webfluxsample.entity.TodoEntity
import com.yunho.webfluxsample.repository.TodoRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TodoService(private val repository: TodoRepository) {

    fun getTodoById(id: Long): Mono<Todo> {
        return repository.findById(id).map { entity -> Todo(entity.id, entity.content) }
    }

    fun getTodos(): Flux<Todo> {
        return repository.findAll().map { entity ->
            Todo(entity.id, entity.content)
        }
    }

    fun createTodos(todo: Todo): Mono<TodoEntity> {
        val entity = TodoEntity(todo.id, todo.content)
        return repository.save(entity)
    }

    fun deleteTodo(id: Long): Mono<Void> {
        return repository.deleteById(id)
    }
}