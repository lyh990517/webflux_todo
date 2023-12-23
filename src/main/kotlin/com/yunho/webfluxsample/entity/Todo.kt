package com.yunho.webfluxsample.entity

import lombok.Data

@Data
data class Todo(
    val id: Int,
    val content: String
)
