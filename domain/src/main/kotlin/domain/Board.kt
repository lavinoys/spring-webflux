package me.lavinoys.domain.domain

import me.lavinoys.domain.entity.BoardEntity

data class Board(
    val id: Long,
    val title: String
) {
    fun Board.toEntity() = BoardEntity(
        id, title
    )
}
