package com.yunho.webfluxsample.entity

import lombok.Data

@Data
data class TodoEntity(
    val id: Int,
    val content: String
)
