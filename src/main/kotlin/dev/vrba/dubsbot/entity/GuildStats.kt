package dev.vrba.dubsbot.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("guild_stats")
data class GuildStats(
    @Id
    val id: Int = 0,

    @Column("guild_id")
    val guild: Long,

    @Column("messages_count")
    val messages: Long = 0,

    @Column("digits_count")
    val digits: Long = 0,

    @Column("extra_matches_count")
    val extra: Long = 0
)