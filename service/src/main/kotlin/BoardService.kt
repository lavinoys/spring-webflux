package me.lavinoys.service

import me.lavinoys.data.db.board.repository.BoardRepository
import me.lavinoys.domain.domain.Board
import me.lavinoys.domain.dto.CreateBoardRequest
import me.lavinoys.domain.entity.BoardEntity
import me.lavinoys.domain.entity.BoardEntity.Companion.toDomain
import org.springframework.stereotype.Service

@Service
class BoardService(
    val repository: BoardRepository
) {

    suspend fun create(param: CreateBoardRequest): Board {
        return repository.save(
            BoardEntity(
                title = param.title
            )
        ).toDomain()
    }
}
