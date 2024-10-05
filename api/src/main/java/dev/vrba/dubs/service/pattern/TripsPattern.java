package dev.vrba.dubs.service.pattern;

import dev.vrba.dubs.domain.Pattern;
import jakarta.inject.Singleton;

@Singleton
public class TripsPattern implements Pattern {
    @Override
    public String getName() {
        return "trips";
    }

    @Override
    public String getEmoji() {
        return "3\uFE0F\u20E3";
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
    public boolean matches(String id) {
        return RepeatingDigitsMatcher.getInstance().countRepeatingTrailingDigits(id) == 3;
    }
}
