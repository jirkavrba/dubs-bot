package dev.vrba.dubs.service.pattern;

import dev.vrba.dubs.domain.Pattern;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;

import java.math.BigInteger;

@Singleton
public class PrimesPattern implements Pattern {
    @Override
    public String getName() {
        return "primes";
    }

    @Override
    public String getEmoji() {
        return "\uD83E\uDD13";
    }

    @Override
    public long getPoints() {
        return 100;
    }

    @Override
    public boolean isRare() {
        return false;
    }

    @Override
    public boolean matches(@NonNull String id) {
        return new BigInteger(id).isProbablePrime(20);
    }
}
