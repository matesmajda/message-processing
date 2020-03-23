package com.hw.query.message;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessageService {

    private MessageRepository messageRepository;

    private MessageEnricher messageEnricher;

    Page<Message> getPage(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Message> messages = messageRepository.findAll(pageRequest);

        List<Message> enrichedMessages = messages.getContent().stream().map(m->messageEnricher.enrichMessage(m)).collect(Collectors.toList());

        return new PageImpl<>(enrichedMessages, pageRequest, messages.getTotalElements());
    }

}
