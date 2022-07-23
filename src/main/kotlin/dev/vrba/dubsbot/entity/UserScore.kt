package dev.vrba.dubsbot.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("user_scores")
data class UserScore(
    @Id
    val id: Int = 0,

    @Column("user_id")
    val user: Long,

    @Column("guild_id")
    val guild: Long,

    @Column("score_date")
    val date: LocalDate,

    @Column("digits_score")
    val digits: IntArray = IntArray(20) { 0 },

    @Column("cons_score")
    val cons: Int = 0,

    @Column("off_by_ones_score")
    val offByOnes: Int = 0,

    @Column("palindromes_score")
    val palindromes: Int = 0,

    @Column("primes_score")
    val primes: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserScore) return false

        return other.id == id
    }

    override fun hashCode(): Int {
        return id
    }
}