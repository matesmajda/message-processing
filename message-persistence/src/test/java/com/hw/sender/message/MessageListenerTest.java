package com.hw.sender.message;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageListenerTest {

    private MessageListener messageListener;

    @Mock
    private MessageService messageService;
    private Gson deserializer = new Gson();

    @BeforeEach
    void setUp() {
        messageListener = new MessageListener(deserializer, messageService);
    }

    @Test
    void serviceIsCalledWithCorrectMessage() {
        Message expectedMessage = Message.builder().content("c").timestamp(1L).build();
        messageListener.addMessage("{'content': 'c', timestamp: 1}");
        verify(messageService).saveMessage(expectedMessage);
    }

    @Test
    void serviceIsNotCalledIfDeserializationFails() {
        messageListener.addMessage("{invalidJson");
        verify(messageService, never()).saveMessage(any());
    }
}