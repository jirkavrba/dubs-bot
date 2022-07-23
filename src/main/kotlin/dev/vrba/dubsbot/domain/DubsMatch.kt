package dev.vrba.dubsbot.domain

sealed class DubsMatch(val emoji: String)

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
    }
)

object ConsMatch : DubsMatch(":1234:")
object OffByOneMatch : DubsMatch(":clown:")
object PalindromeMatch : DubsMatch(":left_right_arrow:")
object PrimeMatch : DubsMatch(":nerd:")