package me.lavinoys.data.db.board.repository

import me.lavinoys.domain.entity.BoardEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface BoardRepository : CoroutineCrudRepository<BoardEntity, Long> {
}
