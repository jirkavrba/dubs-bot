package dev.vrba.dubs.service.pattern;

import dev.vrba.dubs.domain.Pattern;
import jakarta.inject.Singleton;

@Singleton
public class PentsPattern implements Pattern {
    @Override
    public String getName() {
        return "pents";
    }

    @Override
    public String getEmoji() {
        return ":five:";
    }

    @Override
    public long getPoints() {
        return 10000;
    }

    @Override
    public boolean isRare() {
        return true;
    }

    @Override
    public boolean matches(String id) {
        return RepeatingDigitsMatcher.getInstance().countRepeatingTrailingDigits(id) == 5;
    }
}
