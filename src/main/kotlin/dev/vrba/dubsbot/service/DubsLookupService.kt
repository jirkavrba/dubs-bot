package dev.vrba.dubsbot.service

import dev.vrba.dubsbot.domain.*
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class DubsLookupService {

    fun findMatches(message: Long): Set<DubsMatch> {
        val basic = findBasicMatches(message)
        val extra = setOfNotNull(
            findConsMatch(message),
            findOffByOneMatch(message),
            findPalindromeMatch(message),
            findPrimeMatch(message)
        )

        return basic + extra
    }

    private tailrec fun findBasicMatches(message: Long, matches: Set<DubsMatch> = emptySet()): Set<DubsMatch> {
        // A single digit cannot be matched
        if (message <= 10) return emptySet()

        val reversed = message.toString().reversed()
        val first = reversed.first()
        val digits = reversed.takeWhile { it == first }.length

        val remaining = reversed.drop(digits).reversed().let {
            if (it.isEmpty()) 0
            else it.toLong()
        }

        return if (digits < 2) matches
        else findBasicMatches(remaining, matches + BasicMatch(digits))
    }

    private fun findConsMatch(message: Long): ConsMatch? {
        val id = message.toString()
        val cons = "0123456789".windowed(4).any { id.endsWith(it) || id.endsWith(it.reversed()) }

        return if (cons) ConsMatch else null
    }

    // Missing trips or better by just one (offbyone kenobi)
    private fun findOffByOneMatch(message: Long): OffByOneMatch? {
        val reversed = message.toString().reversed()

        val first = reversed[0].digitToInt()
        val matching = listOf(-1, 1).map { first + it }
        val previous = reversed.drop(1).take(2)

        val offByOne = previous[0] == previous[1] && previous[0].digitToInt() in matching

        return if (offByOne) OffByOneMatch else null
    }

    private fun findPalindromeMatch(message: Long): PalindromeMatch? {
        val id = message.toString()
        val palindrome = id == id.reversed()

        return if (palindrome) PalindromeMatch else null
    }

    private fun findPrimeMatch(message: Long): PrimeMatch? {
        // https://en.wikipedia.org/wiki/Miller%E2%80%93Rabin_primality_test
        val prime = BigInteger.valueOf(message).isProbablePrime(512)
        return if (prime) PrimeMatch else null
    }
}