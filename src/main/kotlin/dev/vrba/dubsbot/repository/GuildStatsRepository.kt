package dev.vrba.dubsbot.repository

import dev.vrba.dubsbot.entity.GuildStats
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GuildStatsRepository : CrudRepository<GuildStats, Int> {

    fun findByGuild(guild: Long): GuildStats?

}