package com.yunho.webfluxsample.entity

import lombok.Data
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Data
data class Todo(
    val id: Long,
    val content: String
)
