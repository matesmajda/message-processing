package com.hw.query.message;

import org.springframework.stereotype.Service;

@Service
public class PalindromeCalculator {

    /**
     * Returns the length of the longest palindrome found in a string.
     * <p>
     * The algorithm iterates over all characters of the string,
     * and looks for the longest palindrome with the current character in the center of it.
     */
    public Integer getLongestPalindromeSize(String original) {

        if (original == null || original.length() == 0) {
            return 0;
        }
        String longest = getLongestPalindrome(original);
        return longest.length();
    }

    private String getLongestPalindrome(String original) {
        String longest = "";
        for (int i = 0; i < original.length(); i++) {
            String temp = getLongestAtAnchor(original, i, i);
            if (temp.length() > longest.length()) {
                longest = temp;
            }
            temp = getLongestAtAnchor(original, i, i + 1);
            if (temp.length() > longest.length()) {
                longest = temp;
            }
        }
        return longest;
    }

    private String getLongestAtAnchor(String s, int anchorBegin, int anchorEnd) {
        while (anchorBegin >= 0 && anchorEnd <= s.length() - 1 && s.charAt(anchorBegin) == s.charAt(anchorEnd)) {
            anchorBegin--;
            anchorEnd++;
        }
        return s.substring(anchorBegin + 1, anchorEnd);
    }

}
