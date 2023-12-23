package com.yunho.webfluxsample.handler.dto

import lombok.Data

@Data
data class TodoResponse(
    val id: Int,
    val content: String
)
