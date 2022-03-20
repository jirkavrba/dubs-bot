package dev.vrba.dubsbot.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "scores")
data class Score(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "guild_id", nullable = false)
    val guildId: Long,

    val dubs: Int  = 0,
    val trips: Int = 0,
    val quads: Int = 0,
    val pents: Int = 0
)