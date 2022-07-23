package dev.vrba.dubsbot.entities

import dev.vrba.dubsbot.domain.DubsLookupMode
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "guild_settings")
data class GuildSettings(
        @Id
        val id: Int,

        @Column("guild_id")
        val guild: Long,

        @Column("dubs_lookup_mode")
        val mode: DubsLookupMode = DubsLookupMode.Basic
)