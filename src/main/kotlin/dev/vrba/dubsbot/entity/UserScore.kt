package dev.vrba.dubsbot.entity

import dev.vrba.dubsbot.domain.*
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import kotlin.math.pow

@Table("user_scores")
data class UserScore(
    @Id
    val id: Int = 0,

    @Column("user_id")
    val user: Long,

    @Column("guild_id")
    val guild: Long,

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

    fun total(): Int {
        return digits.mapIndexed { index, count -> count * (10.0).pow(index) } // 10 dubs = 1 trip
            .sum()
            .toInt()
    }

    fun description(): String {
        val digits = digits.mapIndexed { index, count -> if (count > 0) "**$count×** " + BasicMatch(index).emoji else null }
        val extra = listOf(
            if (cons > 0) "**$cons×** ${ConsMatch.emoji}" else null,
            if (primes > 0) "**$primes×** ${PrimeMatch.emoji}" else null,
            if (offByOnes > 0) "**$offByOnes×** ${OffByOneMatch.emoji}" else null,
            if (palindromes > 0) "**$palindromes×*8 ${PalindromeMatch.emoji}" else null,
        )

        return "<@${user}>: " + (digits.reversed() + extra).filterNotNull().joinToString(" ")
    }
}