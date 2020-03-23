package com.hw.socket.message;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageListenerTest {

    private static final String WS_DESTINATION = "WS_DESTINATION";

    @Mock
    private SimpMessagingTemplate messagingTemplate;
    private Gson deserializer = new Gson();
    private MessageListener messageListener;

    @BeforeEach
    void setUp() {
        messageListener = new MessageListener(deserializer, messagingTemplate, WS_DESTINATION);
    }

    @Test
    void messageIsSentToDestination() {
        Message expectedMessage = Message.builder().content("c").timestamp(1L).build();
        messageListener.messageReceived("{'content': 'c', timestamp: 1}");
        verify(messagingTemplate).convertAndSend(WS_DESTINATION, expectedMessage);
    }

    @Test
    void invalidMessageIsIgnored() {
        messageListener.messageReceived("{invalidJson");
        verifyNoInteractions(messagingTemplate);
    }
}