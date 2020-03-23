package com.hw.sender.message;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Component
@Slf4j
@AllArgsConstructor
public class MessageListener {

    private Gson messageDeserializer;
    private MessageService messageService;

    @KafkaListener(topics = "${message.topic}")
    public void addMessage(@RequestBody String messageJson) {
        log.info("Received message: {}", messageJson);

        Message message;
        try {
            message = messageDeserializer.fromJson(messageJson, Message.class);
        } catch (Exception e) {
            log.error("Failed to deserialize message. Exception:", e);
            return;
        }
        messageService.saveMessage(message);
    }
}
