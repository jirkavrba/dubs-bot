package dev.vrba.dubs.service.pattern;

import dev.vrba.dubs.domain.Pattern;
import jakarta.inject.Singleton;

@Singleton
public class QuadsPattern implements Pattern {
    @Override
    public String getName() {
        return "quads";
    }

    @Override
    public String getEmoji() {
        return ":four:";
    }

    @Override
    public long getPoints() {
        return 1000;
    }

    @Override
    public boolean isRare() {
        return true;
    }

    @Override
    public boolean matches(String id) {
        return RepeatingDigitsMatcher.getInstance().countRepeatingTrailingDigits(id) == 4;
    }
}
