package com.hw.query.message;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@AllArgsConstructor
public class MessageService {

    private MessageRepository messageRepository;

    Page<Message> getPage(Integer pageNumber, Integer pageSize) {
        return messageRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

}
