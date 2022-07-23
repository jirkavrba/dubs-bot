package dev.vrba.dubsbot.repository

import dev.vrba.dubsbot.entity.GuildSettings
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GuildSettingsRepository : CrudRepository<GuildSettings, Int> {

    fun findByGuild(guild: Long): GuildSettings?

}