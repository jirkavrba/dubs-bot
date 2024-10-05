package dev.vrba.dubs.service.pattern;

import dev.vrba.dubs.domain.Pattern;
import jakarta.inject.Singleton;

@Singleton
public class SextsPattern implements Pattern {
    @Override
    public String getName() {
        return "sexts";
    }

    @Override
    public String getEmoji() {
        return "6\uFE0F\u20E3";
    }

    @Override
    public long getPoints() {
        return 100000;
    }

    @Override
    public boolean isRare() {
        return true;
    }

    @Override
    public boolean matches(String id) {
        return RepeatingDigitsMatcher.getInstance().countRepeatingTrailingDigits(id) == 6;
    }
}
