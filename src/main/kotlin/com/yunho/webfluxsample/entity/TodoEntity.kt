package com.yunho.webfluxsample.entity

import lombok.Data
import lombok.Generated
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Data
@Table("TODO")
data class TodoEntity(
    @Id
    val id: Long?,
    val content: String
)
