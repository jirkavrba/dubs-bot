package dev.vrba.dubsbot.repositories

import dev.vrba.dubsbot.entities.Score
import org.springframework.data.domain.Sort
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ScoreRepository : CrudRepository<Score, UUID> {
    fun findByUserIdAndGuildId(userId: Long, guildId: Long): Score?
    fun findByGuildId(guildId: Long, sort: Sort): List<Score>

}