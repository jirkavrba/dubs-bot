package dev.vrba.dubs.service.pattern;

import dev.vrba.dubs.domain.Pattern;
import jakarta.inject.Singleton;

@Singleton
public class NonasPattern implements Pattern {
    @Override
    public String getName() {
        return "nonas";
    }

    @Override
    public String getEmoji() {
        return ":nine:";
    }

    @Override
    public long getPoints() {
        return 100000000;
    }

    @Override
    public boolean isRare() {
        return true;
    }

    @Override
    public boolean matches(String id) {
        return RepeatingDigitsMatcher.getInstance().countRepeatingTrailingDigits(id) == 9;
    }
}
