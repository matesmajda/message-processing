package com.hw.socket.message;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class Message {

    private String content;
    private Long timestamp;
}
