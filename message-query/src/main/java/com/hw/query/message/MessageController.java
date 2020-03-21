package com.hw.query.message;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MessageController {

    private MessageService messageService;

    @GetMapping(value = "/message")
    public Page<Message> addMessage() {
        return messageService.getPage();
    }

}
