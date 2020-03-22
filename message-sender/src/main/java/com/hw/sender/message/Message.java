package com.hw.sender.message;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Data
@Builder
class Message {

    @NotNull
    @Size(min = 1, max = 255)
    private String content;

    @NotNull
    @JsonDeserialize(using = CustomInstantDeserializer.class)
    @ApiModelProperty(example = "2020-03-20 11:11:11+0000")
    private Instant timestamp;
}
