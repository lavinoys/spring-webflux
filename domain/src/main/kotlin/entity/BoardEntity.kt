package me.lavinoys.domain.entity

import me.lavinoys.domain.domain.Board
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("boards")
data class BoardEntity(
    @Id
    val id: Long = 0L,
    val title: String
) {
    companion object {
        fun BoardEntity.toDomain() = Board(
            id = this.id,
            title = this.title
        )
    }
}
