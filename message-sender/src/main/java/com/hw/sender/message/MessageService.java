package com.hw.sender.message;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {

    @Autowired
    private KafkaTemplate<String, String> template;

    void sendMessage(MessageRequest messageRequest) {

        String messageJson = new Gson().toJson(messageRequest);
        template.send("myTopic", messageJson);
    }
}
