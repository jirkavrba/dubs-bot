package dev.vrba.dubsbot.repository

import dev.vrba.dubsbot.entity.GuildSettings
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GuildSettingsRepository : CrudRepository<GuildSettings, Int> {

    @Cacheable("guilds")
    fun findByGuild(guild: Long): GuildSettings?

    @CacheEvict("guilds")
    override fun <S : GuildSettings> save(entity: S): S
}