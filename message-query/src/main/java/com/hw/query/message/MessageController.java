package com.hw.query.message;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@AllArgsConstructor
@Validated
public class MessageController {

    private MessageService messageService;

    @GetMapping(value = "/message")
    public Page<EnrichedMessage> getMessages(
            @RequestParam("pageNumber") @NotNull @Min(0) Integer pageNumber,
            @RequestParam("pageSize") @NotNull @Positive Integer pageSize) {
        return messageService.getPage(pageNumber, pageSize);
    }

}
