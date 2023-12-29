package com.yunho.webfluxsample.dto

import lombok.Data

@Data
data class TodoResponse(
    val id: Long,
    val content: String
)
