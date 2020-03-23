package com.hw.socket.message;

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
    public void messageReceived(@RequestBody String messageJson) {
        log.info("Received message: {}", messageJson);
        Message message;
        try {
            message = messageDeserializer.fromJson(messageJson, Message.class);
        } catch (Exception e) {
            log.error("Ignoring invalid message", e);
            return;
        }
        template.convertAndSend(wsDestination, message);
    }
}
