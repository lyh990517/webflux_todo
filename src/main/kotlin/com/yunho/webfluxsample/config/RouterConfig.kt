package com.yunho.webfluxsample.config

import com.yunho.webfluxsample.handler.TodoHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class RouterConfig(private val todoHandler: TodoHandler) {

    @Bean
    fun route() = router {
        "/api".nest {
            GET("/{id}", todoHandler::getTodoById)
            GET("", todoHandler::getTodos)
            POST("",todoHandler::createTodo)
            DELETE("/{id}",todoHandler::deleteTodo)
        }
    }
}