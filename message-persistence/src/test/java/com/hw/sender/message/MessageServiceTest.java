package com.hw.sender.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    private MessageService messageService;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private MessageValidator messageValidator;

    @BeforeEach
    void setUp() {
        messageService = new MessageService(messageRepository, messageValidator);
    }

    @Test
    void messageIsSaved() {
        Message message = Message.builder().content("c").timestamp(1L).build();
        when(messageValidator.isValid(message)).thenReturn(true);

        messageService.saveMessage(message);

        verify(messageRepository).save(message);
    }

    @Test
    void invalidMessagesAreIgnored() {
        when(messageValidator.isValid(any())).thenReturn(false);
        messageService.saveMessage(Message.builder().build());

        verify(messageRepository, never()).save(any());
    }

}