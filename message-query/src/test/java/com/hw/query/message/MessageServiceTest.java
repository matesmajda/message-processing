package com.hw.query.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    private MessageService messageService;
    private MessageEnricher enricher = new MessageEnricher(new PalindromeCalculator());

    @Mock
    private MessageRepository repository;

    @BeforeEach
    void setUp() {
        messageService = new MessageService(repository, enricher);
    }

    @Test
    void name() {
        Message message1 = Message.builder().content("abba").timestamp(1L).build();
        Message message2 = Message.builder().content("abc").timestamp(2L).build();
        Message expectedMessage1 = EnrichedMessage.builder().longestPalindromeSize(4).content("abba").timestamp(1L).build();
        Message expectedMessage2 = EnrichedMessage.builder().longestPalindromeSize(1).content("abc").timestamp(2L).build();

        when(repository.findAll(PageRequest.of(1, 2))).thenReturn(new PageImpl<>(asList(message1, message2)));

        Page<EnrichedMessage> result = messageService.getPage(1, 2);

        assertEquals(expectedMessage1, result.getContent().get(0));
        assertEquals(expectedMessage2, result.getContent().get(1));
    }
}