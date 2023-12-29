package com.yunho.webfluxsample.dto

import com.yunho.webfluxsample.entity.Todo
import lombok.Data

@Data
data class TodoListResponse(
    val list : List<Todo>
)
