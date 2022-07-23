package dev.vrba.dubsbot.domain

enum class DubsLookupMode {
    // Only search for dubs, trips, quads...
    Basic,

    // Also enable searching for cons, primes, palindromes and other esoteric number patterns
    Extended,
}