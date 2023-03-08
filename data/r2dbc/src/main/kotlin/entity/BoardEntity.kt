package me.lavinoys.data.r2dbc.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("boards")
data class BoardEntity(
    @Id
    val id: Long = 0L,
    val title: String
)
