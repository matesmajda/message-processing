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

    @KafkaListener(topics = "message_topic5")
    public void addMessage(@RequestBody String message) {
        Message m = messageDeserializer.fromJson(message, Message.class);
        log.info("Received message: {}", m);
    }
}
