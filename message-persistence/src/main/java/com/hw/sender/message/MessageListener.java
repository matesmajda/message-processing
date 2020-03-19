package com.hw.sender.message;

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

//    private MessageService messageService;

    @KafkaListener(topics = "message_topic", containerFactory = "kafkaListenerContainerFactory")
    public void addMessage(@RequestBody Message message) {
        log.info("Received message: {}", message);
//        messageService.sendMessage(messageRequest);
    }

}
