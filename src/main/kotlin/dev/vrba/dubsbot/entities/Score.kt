package dev.vrba.dubsbot.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.util.UUID

@Entity
@Table(name = "scores", uniqueConstraints = [
    UniqueConstraint(name = "UK_user_guild", columnNames = ["user_id", "guild_id"])
])
data class Score(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "guild_id", nullable = false)
    val guildId: Long,

    @Column(name = "dubs", nullable = false)
    val dubs: Int = 0,

    @Column(name = "trips", nullable = false)
    val trips: Int = 0,

    @Column(name = "quads", nullable = false)
    val quads: Int = 0,

    @Column(name = "pents", nullable = false)
    val pents: Int = 0
) {
    constructor() : this(UUID.randomUUID(), 0, 0)
}