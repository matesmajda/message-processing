package com.hw.query.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class EnrichedMessage extends Message {

    private Integer longestPalindromeSize;
}
