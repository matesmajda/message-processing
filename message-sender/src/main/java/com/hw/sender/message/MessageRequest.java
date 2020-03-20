package com.hw.sender.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import lombok.Data;

import java.time.Instant;

@Data
class MessageRequest {

    private String content;

    @JsonSerialize(using = InstantSerializer.class)
    @JsonDeserialize(using = CustomInstantDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Instant timestamp;
}
