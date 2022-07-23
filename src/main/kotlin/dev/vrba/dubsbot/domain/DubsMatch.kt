package dev.vrba.dubsbot.domain

import dev.vrba.dubsbot.entity.UserScore

sealed class DubsMatch(val emoji: String, val name: String, val apply: (UserScore) -> UserScore)

class BasicMatch(val digits: Int) : DubsMatch(
    when (digits) {
        2 -> ":two:"
        3 -> ":three:"
        4 -> ":four:"
        5 -> ":five:"
        6 -> ":six:"
        7 -> ":seven:"
        8 -> ":eight:"
        9 -> ":nine:"
        else -> ":ten:"
    },
    when (digits) {
        2 -> "dubs"
        3 -> "trips"
        4 -> "quads"
        5 -> "pents"
        6 -> "sextas"
        7 -> "septas"
        8 -> "octas"
        9 -> "nonas"
        else -> "MOOORE!"
    },
    { score -> score.copy(digits = score.digits.also { it[digits]++ }) }
)

object ConsMatch : DubsMatch(":1234:", "Cons", { score -> score.copy(cons = score.cons + 1) })
object OffByOneMatch : DubsMatch(":clown:", "Offbyone Kenobi", { score -> score.copy(offByOnes = score.offByOnes + 1) })
object PalindromeMatch : DubsMatch(":left_right_arrow:", "Palindrome", { score -> score.copy(palindromes = score.palindromes + 1) })
object PrimeMatch : DubsMatch(":nerd:", "Prime", { score -> score.copy(primes = score.primes + 1) })