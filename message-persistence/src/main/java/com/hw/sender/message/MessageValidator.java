package com.hw.sender.message;

import org.springframework.stereotype.Component;

@Component
public class MessageValidator {

    boolean isValid(Message message) {
        return message != null && message.getTimestamp() != null && message.getContent() != null;
    }

}
