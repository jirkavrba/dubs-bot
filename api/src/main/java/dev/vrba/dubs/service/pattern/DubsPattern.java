package dev.vrba.dubs.service.pattern;

import dev.vrba.dubs.domain.Pattern;
import jakarta.inject.Singleton;

@Singleton
public class DubsPattern implements Pattern {
    @Override
    public String getName() {
        return "dubs";
    }

    @Override
    public String getEmoji() {
        return "2\uFE0F\u20E3";
    }

    @Override
    public long getPoints() {
        return 10;
    }

    @Override
    public boolean isRare() {
        return false;
    }

    @Override
    public boolean matches(String id) {
        return RepeatingDigitsMatcher.getInstance().countRepeatingTrailingDigits(id) == 2;
    }
}
