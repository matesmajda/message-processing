package com.hw.sender.message;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MessageController {

    private MessageService messageService;

    @CrossOrigin("*")
    @PostMapping(value = "/message", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addMessage(@RequestBody Message message) {
        messageService.sendMessage(message);
    }

}
