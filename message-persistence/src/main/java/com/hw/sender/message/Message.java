package com.hw.sender.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Message {

    private String content;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ssZ")
    private Date timestamp;
}
