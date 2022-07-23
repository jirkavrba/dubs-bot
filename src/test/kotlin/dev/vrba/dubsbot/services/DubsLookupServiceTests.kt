package dev.vrba.dubsbot.services

import dev.vrba.dubsbot.domain.BasicMatch
import dev.vrba.dubsbot.domain.DubsLookupMode
import dev.vrba.dubsbot.domain.PrimeMatch
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
    fun `test prime matches lookup`() {
        assertTrue(service.findMatches(4346075790683833919, DubsLookupMode.Extended)
                .filterIsInstance<PrimeMatch>()
                .count() == 1
        )

        assertTrue(service.findMatches(4346075790683833912, DubsLookupMode.Extended)
                .filterIsInstance<PrimeMatch>()
                .isEmpty()
        )
    }
}