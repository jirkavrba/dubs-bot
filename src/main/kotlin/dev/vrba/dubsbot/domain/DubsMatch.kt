package dev.vrba.dubsbot.domain

sealed class DubsMatch(val emoji: String, val name: String)

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
    }
)

object ConsMatch : DubsMatch(":1234:", "Cons")
object OffByOneMatch : DubsMatch(":clown:", "Offbyone Kenobi")
object PalindromeMatch : DubsMatch(":left_right_arrow:", "Palindrome")
object PrimeMatch : DubsMatch(":nerd:", "Prime")