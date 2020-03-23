package com.hw.query.message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeCalculatorTest {

    private PalindromeCalculator palindromeCalculator = new PalindromeCalculator();

    @Test
    void zeroReturnedForEmptyStrings() {
        Integer result = palindromeCalculator.getLongestPalindromeSize("");
        assertEquals(0, result);
    }

    @Test
    void zeroReturnedForNull() {
        Integer result = palindromeCalculator.getLongestPalindromeSize(null);
        assertEquals(0, result);
    }

    @Test
    void oneLongString() {
        Integer result = palindromeCalculator.getLongestPalindromeSize("a");
        assertEquals(1, result);
    }

    @Test
    void evenLongString() {
        Integer result = palindromeCalculator.getLongestPalindromeSize("abbaabbaff");
        assertEquals(8, result);
    }

    @Test
    void oddLongString() {
        Integer result = palindromeCalculator.getLongestPalindromeSize("fabbaabba");
        assertEquals(8, result);
    }
}