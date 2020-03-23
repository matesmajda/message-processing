package com.hw.query.message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageEnricherTest {

    private PalindromeCalculator palindromeCalculator = new PalindromeCalculator();
    private MessageEnricher messageEnricher = new MessageEnricher(palindromeCalculator);

    @Test
    void messageIsEnriched() {
        EnrichedMessage expected = EnrichedMessage.builder().content("cabba").timestamp(1L).longestPalindromeSize(4).build();
        Message original = Message.builder().content("cabba").timestamp(1L).build();
        EnrichedMessage enrichedMessage = messageEnricher.enrichMessage(original);

        assertEquals(expected, enrichedMessage);
    }
}