package com.hw.sender.message;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@Slf4j
public class MessageListener {

    private Gson messageDeserializer;
    private SimpMessagingTemplate template;
    private String wsDestination;

    public MessageListener(Gson messageDeserializer, SimpMessagingTemplate template, @Value("${websocket.destination.messages}") String wsDestination) {
        this.messageDeserializer = messageDeserializer;
        this.template = template;
        this.wsDestination = wsDestination;
    }

    @KafkaListener(topics = "${message.topic}")
    public void addMessage(@RequestBody String messageJson) {
        log.info("Received message: {}", messageJson);
        Message message = messageDeserializer.fromJson(messageJson, Message.class);
        template.convertAndSend(wsDestination, message);
    }
}
