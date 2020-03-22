package com.hw.sender.message;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Validated
public class MessageController {

    private MessageService messageService;

    @CrossOrigin("*")
    @PostMapping(value = "/message", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addMessage(@Valid @RequestBody Message message) {
        messageService.sendMessage(message);
    }

}
