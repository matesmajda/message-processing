package com.hw.query.message;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MessageEnricher {

    private PalindromeCalculator palindromeCalculator;

    EnrichedMessage enrichMessage(Message message) {
        return EnrichedMessage.builder()
                .longestPalindromeSize(palindromeCalculator.getLongestPalindromeSize(message.getContent()))
                .content(message.getContent())
                .timestamp(message.getTimestamp()).build();

    }
}
