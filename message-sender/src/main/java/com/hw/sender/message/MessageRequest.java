package com.hw.sender.message;

import lombok.Data;

import java.util.Date;

@Data
class MessageRequest {

    private String content;
    private Date timestamp;
}
