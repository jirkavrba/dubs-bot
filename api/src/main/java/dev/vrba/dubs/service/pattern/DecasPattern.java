package dev.vrba.dubs.service.pattern;

import dev.vrba.dubs.domain.Pattern;
import jakarta.inject.Singleton;

@Singleton
public class DecasPattern implements Pattern {
    @Override
    public String getName() {
        return "decas";
    }

    @Override
    public String getEmoji() {
        return "\uD83D\uDD1F";
    }

    @Override
    public long getPoints() {
        return 1000000000;
    }

    @Override
    public boolean isRare() {
        return true;
    }

    @Override
    public boolean matches(String id) {
        return RepeatingDigitsMatcher.getInstance().countRepeatingTrailingDigits(id) >= 10;
    }
}
