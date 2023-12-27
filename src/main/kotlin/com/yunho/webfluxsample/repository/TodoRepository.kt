package com.yunho.webfluxsample.repository

import com.yunho.webfluxsample.entity.TodoEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.lang.RuntimeException

@Repository
interface TodoRepository : R2dbcRepository<TodoEntity,Long>{
}