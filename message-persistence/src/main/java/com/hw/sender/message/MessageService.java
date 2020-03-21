package com.hw.sender.message;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {

    private MessageRepository messageRepository;

    void saveMessage(Message message) {
        messageRepository.save(message);
    }

}
