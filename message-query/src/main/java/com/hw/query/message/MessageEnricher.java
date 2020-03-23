package com.hw.query.message;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MessageEnricher {

    private PalindromeCalculator palindromeCalculator;

    EnrichedMessage enrichMessage(Message message) {
        Integer longestPalindromeSize = palindromeCalculator.getLongestPalindromeSize(message.getContent());
        return EnrichedMessage.of(message, longestPalindromeSize);
    }
}
