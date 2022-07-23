package dev.vrba.dubsbot.services

import dev.vrba.dubsbot.domain.*
import org.springframework.stereotype.Service

@Service
class DubsLookupService {

    fun findMatches(message: Long, mode: DubsLookupMode): Set<DubsMatch> {
        val basic = findBasicMatches(message)

        if (mode == DubsLookupMode.Basic) {
            return basic
        }

        return basic + setOfNotNull(
                findConsMatch(message),
                findOffByOneMatch(message),
                findPalindromeMatch(message),
                findPrimeMatch(message)
        )
    }

    private tailrec fun findBasicMatches(message: Long, matches: Set<DubsMatch> = emptySet()): Set<DubsMatch> {
        // A single digit cannot be matched
        if (message <= 10) return emptySet()

        val reversed = message.toString().reversed()
        val first = reversed.first()
        val digits = reversed.takeWhile { it == first }.length

        val remaining = reversed.drop(digits).reversed().toLong()

        return if (digits < 2) matches
        else findBasicMatches(remaining, matches + BasicMatch(digits))
    }

    private fun findConsMatch(message: Long): ConsMatch? {
        return null
    }

    private fun findOffByOneMatch(message: Long): OffByOneMatch? {
        return null
    }

    private fun findPalindromeMatch(message: Long): PalindromeMatch? {
        return null
    }

    private fun findPrimeMatch(message: Long): PrimeMatch? {
        return null
    }
}