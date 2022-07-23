package dev.vrba.dubsbot.service

import dev.vrba.dubsbot.domain.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DubsLookupServiceTests {

    private val service = DubsLookupService()

    @Test
    fun `test basic matches lookup`() {
        assertTrue(service.findMatches(4346075790683833901, DubsLookupMode.Basic).isEmpty())

        assertTrue(service.findMatches(4346075790683833900, DubsLookupMode.Basic).let {
            it.isNotEmpty() &&
                    it.filterIsInstance<BasicMatch>().size == 1 &&
                    it.filterIsInstance<BasicMatch>().filter { match -> match.digits == 2 }.size == 1
        })

        assertTrue(service.findMatches(4346075790683839000, DubsLookupMode.Basic).let {
            it.isNotEmpty() &&
                    it.size == 1 &&
                    it.filterIsInstance<BasicMatch>().size == 1 &&
                    it.filterIsInstance<BasicMatch>().filter { match -> match.digits == 3 }.size == 1
        })

        assertTrue(service.findMatches(4346075790683830000, DubsLookupMode.Basic).let {
            it.isNotEmpty() &&
                    it.size == 1 &&
                    it.filterIsInstance<BasicMatch>().size == 1 &&
                    it.filterIsInstance<BasicMatch>().filter { match -> match.digits == 4 }.size == 1
        })

        assertTrue(service.findMatches(4346075790883833300, DubsLookupMode.Basic).let {
            it.isNotEmpty() &&
                    it.size == 2 &&
                    it.filterIsInstance<BasicMatch>().size == 2 &&
                    it.filterIsInstance<BasicMatch>().filter { match -> match.digits == 2 }.size == 1 &&
                    it.filterIsInstance<BasicMatch>().filter { match -> match.digits == 3 }.size == 1
        })

        assertTrue(service.findMatches(4346075798888800000, DubsLookupMode.Basic).let {
            it.isNotEmpty() &&
                    it.size == 2 &&
                    it.filterIsInstance<BasicMatch>().size == 2 &&
                    it.filterIsInstance<BasicMatch>().filter { match -> match.digits == 5 }.size == 2
        })
    }

    @Test
    fun `test cons matches lookup`() {
        assertTrue(service.findMatches(4346075798888800012, DubsLookupMode.Extended).none { it is ConsMatch })
        assertTrue(service.findMatches(4346075798888804312, DubsLookupMode.Extended).none { it is ConsMatch })
        assertTrue(service.findMatches(4346075798888809012, DubsLookupMode.Extended).none { it is ConsMatch })
        assertTrue(service.findMatches(4346075798888809876, DubsLookupMode.Extended).any { it is ConsMatch })
        assertTrue(service.findMatches(4346075798888806543, DubsLookupMode.Extended).any { it is ConsMatch })
        assertTrue(service.findMatches(4346075798888801234, DubsLookupMode.Extended).any { it is ConsMatch })
        assertTrue(service.findMatches(4346075798888806789, DubsLookupMode.Extended).any { it is ConsMatch })
    }

    @Test
    fun `test off by one matches lookup`() {
        assertTrue(service.findMatches(4346075798888806777, DubsLookupMode.Extended).none { it is OffByOneMatch })
        assertTrue(service.findMatches(4346075798888806122, DubsLookupMode.Extended).none { it is OffByOneMatch })
        assertTrue(service.findMatches(4346075798888811009, DubsLookupMode.Extended).none { it is OffByOneMatch })
        assertTrue(service.findMatches(4346075798888811990, DubsLookupMode.Extended).none { it is OffByOneMatch })
        assertTrue(service.findMatches(4346075798888806778, DubsLookupMode.Extended).any { it is OffByOneMatch })
        assertTrue(service.findMatches(4346075798888806887, DubsLookupMode.Extended).any { it is OffByOneMatch })
        assertTrue(service.findMatches(4346075798888811112, DubsLookupMode.Extended).any { it is OffByOneMatch })
    }

    @Test
    fun `test palindrome matches lookup`() {
        assertTrue(service.findMatches(1234566778776654311, DubsLookupMode.Extended).none { it is PalindromeMatch })
        assertTrue(service.findMatches(1234566778776654322, DubsLookupMode.Extended).none { it is PalindromeMatch })
        assertTrue(service.findMatches(1234566738776654333, DubsLookupMode.Extended).none { it is PalindromeMatch })
        assertTrue(service.findMatches(1234566778876654321, DubsLookupMode.Extended).none { it is PalindromeMatch })
        assertTrue(service.findMatches(1234566778776654321, DubsLookupMode.Extended).any { it is PalindromeMatch })
        assertTrue(service.findMatches(1111111111111111111, DubsLookupMode.Extended).any { it is PalindromeMatch })
        assertTrue(service.findMatches(1100110010100110011, DubsLookupMode.Extended).any { it is PalindromeMatch })
        assertTrue(service.findMatches(1234561234321654321, DubsLookupMode.Extended).any { it is PalindromeMatch })
    }

    @Test
    fun `test prime matches lookup`() {
        assertTrue(service.findMatches(4346075790683833919, DubsLookupMode.Extended)
                .filterIsInstance<PrimeMatch>()
                .count() == 1
        )

        assertTrue(service.findMatches(4346075790683833912, DubsLookupMode.Extended)
                .filterIsInstance<PrimeMatch>()
                .isEmpty()
        )

        // TODO: Add more prime test (I won't lol)
    }
}