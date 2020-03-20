package com.hw.sender.message;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {

    private KafkaTemplate<String, String> template;
    private Gson messageSerializer;

    void sendMessage(Message message) {

        String messageJson = messageSerializer.toJson(message);
        template.send("message_topic5", messageJson);
    }
}
