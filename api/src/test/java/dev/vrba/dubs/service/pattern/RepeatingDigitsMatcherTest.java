package dev.vrba.dubs.service.pattern;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepeatingDigitsMatcherTest {

    @ParameterizedTest
    @CsvSource({
            ",0",
            "'',0",
            "10000001,1",
            "11121355,2",
            "11121555,3",
            "11124444,4",
            "44444444,8",
    })
    void shouldCountTrailingDigits(String number, int expectedResult) {
        assertEquals(expectedResult, RepeatingDigitsMatcher.getInstance().countRepeatingTrailingDigits(number));
    }
}
