package me.lavinoys.data.r2dbc.repository

import me.lavinoys.data.r2dbc.entity.BoardEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface BoardRepository : CoroutineCrudRepository<BoardEntity, Long> {
}
