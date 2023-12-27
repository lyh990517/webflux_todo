package com.yunho.webfluxsample.entity

import lombok.Data

@Data
data class Todo(
    val id: Long,
    val content: String
)
