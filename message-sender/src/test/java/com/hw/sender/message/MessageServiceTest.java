package com.hw.sender.message;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    private static final String TOPIC_NAME = "message_topic";
    private MessageService messageService;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson serializer = new Gson();

    @BeforeEach
    void setUp() {
        messageService = new MessageService(TOPIC_NAME, kafkaTemplate, serializer);
    }

    @Test
    void messageIsSentToTopic() {
        Message message = Message.builder().content("content").timestamp(new Date()).build();
        String serializedMessage = serializer.toJson(message);
        messageService.sendMessage(message);

        Mockito.verify(kafkaTemplate).send(TOPIC_NAME, serializedMessage);
    }
}