package com.hw.sender.message;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MessageService {

    private MessageRepository messageRepository;
    private MessageValidator messageValidator;

    void saveMessage(Message message) {

        if (!messageValidator.isValid(message)) {
            log.warn("Ignoring envalid message: {}", message);
            return;
        }
        trySaveMessage(message);
    }

    private void trySaveMessage(Message message) {
        try {
            messageRepository.save(message);
        } catch (Exception e) {
            log.error("Failed to save message: {}", message);
        }
    }

}
