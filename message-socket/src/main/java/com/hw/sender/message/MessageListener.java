package com.hw.sender.message;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Component
@Slf4j
@AllArgsConstructor
public class MessageListener {

    private Gson messageDeserializer;
    private SimpMessagingTemplate template;

    @KafkaListener(topics = "${message.topic}")
    public void addMessage(@RequestBody String messageJson) {
        log.info("Received message: {}", messageJson);
        Message message = messageDeserializer.fromJson(messageJson, Message.class);
        template.convertAndSend("/topic/messages", message);
    }
}
