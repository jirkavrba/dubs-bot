package dev.vrba.dubsbot.repositories

import dev.vrba.dubsbot.entities.Score
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ScoresRepository : CrudRepository<Score, UUID> {

    fun findByUserIdAndGuildId(userId: Long, guildId: Long): Score?

    @Query("""
       select new Score(id, userId, guildId, dubs, trips, quads, pents) from Score
       order by pents desc, quads desc, trips desc, dubs desc
    """)
    fun guildLeaderboard(guildId: Long): List<Score>
}