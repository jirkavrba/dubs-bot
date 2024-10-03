package dev.vrba.dubs.service.pattern;

import dev.vrba.dubs.domain.Pattern;
import jakarta.inject.Singleton;

@Singleton
public class OctasPattern implements Pattern {
    @Override
    public String getName() {
        return "octas";
    }

    @Override
    public String getEmoji() {
        return ":eight:";
    }

    @Override
    public long getPoints() {
        return 10000000;
    }

    @Override
    public boolean isRare() {
        return true;
    }

    @Override
    public boolean matches(String id) {
        return RepeatingDigitsMatcher.getInstance().countRepeatingTrailingDigits(id) == 8;
    }
}
