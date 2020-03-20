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
public class MessageListener {

    @KafkaListener(topics = "message_topic5")
    public void addMessage(@RequestBody String message) {

        Gson gson = new Gson();
        Message m = gson.fromJson(message, Message.class);
        log.info("Received message: {}", m);
    }
}
