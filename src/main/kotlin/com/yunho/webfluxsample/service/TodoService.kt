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
        return repository.findById(id).map { entity -> Todo(entity.id ?: 0, entity.content) }
    }

    fun getTodos(): Flux<Todo> {
        return repository.findAll().map { entity ->
            Todo(entity.id ?: 0, entity.content)
        }
    }

    fun createTodos(todo: Todo) {
        repository.save(TodoEntity(null, todo.content)).subscribe()
    }

    fun deleteTodo(id: Long) {
        repository.deleteById(id).subscribe()
    }
}