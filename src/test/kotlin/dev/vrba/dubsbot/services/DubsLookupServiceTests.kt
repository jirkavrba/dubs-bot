package dev.vrba.dubsbot.services

import dev.vrba.dubsbot.domain.DubsLookupMode
import dev.vrba.dubsbot.domain.PrimeMatch
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DubsLookupServiceTests {

    private val service = DubsLookupService()

    @Test
    fun `test prime matches lookup`() {
        assertTrue(
                service.findMatches(4346075790683833919, DubsLookupMode.Extended)
                        .filterIsInstance<PrimeMatch>()
                        .count() == 1
        )

        assertTrue(
                service.findMatches(4346075790683833912, DubsLookupMode.Extended)
                        .filterIsInstance<PrimeMatch>()
                        .isEmpty()
        )
    }
}