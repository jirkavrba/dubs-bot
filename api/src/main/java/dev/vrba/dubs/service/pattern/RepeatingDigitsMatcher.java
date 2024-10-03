package dev.vrba.dubs.service.pattern;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import jakarta.validation.constraints.Null;

public class RepeatingDigitsMatcher {

    private static final RepeatingDigitsMatcher INSTANCE = new RepeatingDigitsMatcher();

    @NonNull
    public static RepeatingDigitsMatcher getInstance() {
        return INSTANCE;
    }

    @NonNull
    public int countRepeatingTrailingDigits(@Nullable String number) {
        if (number == null || number.isBlank()) {
            return 0;
        }

        final String reversed = new StringBuilder()
                .append(number)
                .reverse()
                .toString();

        final char desiredDigit = reversed.charAt(0);

        return reversed.chars()
                .skip(1)
                .takeWhile(it -> it == desiredDigit)
                .toArray()
                .length + 1;
    }

}
