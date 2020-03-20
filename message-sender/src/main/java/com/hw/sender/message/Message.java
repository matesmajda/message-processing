package com.hw.sender.message;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.Instant;

@Data
class Message {

    private String content;

    @JsonDeserialize(using = CustomInstantDeserializer.class)
    @ApiModelProperty(example = "2020-03-20 11:11:11+0000")
    private Instant timestamp;
}
