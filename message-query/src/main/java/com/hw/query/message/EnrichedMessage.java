package com.hw.query.message;

import lombok.Data;

import java.util.Date;

@Data
public class EnrichedMessage {

    private EnrichedMessage(String content, Date timestamp, Integer longestPalindromeSize) {
        this.content = content;
        this.timestamp = timestamp;
        this.longestPalindromeSize = longestPalindromeSize;
    }

    static EnrichedMessage of(Message message, Integer longestPalindromeSize) {
        return new EnrichedMessage(message.getContent(), new Date(message.getTimestamp()), longestPalindromeSize);
    }

    private Date timestamp;
    private String content;
    private Integer longestPalindromeSize;
}
