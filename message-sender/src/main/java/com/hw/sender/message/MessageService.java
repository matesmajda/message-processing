package com.hw.sender.message;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageService {

    private String messageTopic;
    private KafkaTemplate<String, String> template;
    private Gson messageSerializer;

    @Autowired
    public MessageService(@Value("${message.topic}") String messageTopic, KafkaTemplate<String, String> template, Gson messageSerializer) {
        this.messageTopic = messageTopic;
        this.template = template;
        this.messageSerializer = messageSerializer;
    }

    void sendMessage(Message message) {
        log.info("Sending message: {}", message);
        String messageJson = messageSerializer.toJson(message);
        template.send(messageTopic, messageJson);
    }
}
