package com.hw.query.message;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {

    private MessageRepository messageRepository;

    Page<Message> getPage() {
        return messageRepository.findAll(Pageable.unpaged());
    }

}
