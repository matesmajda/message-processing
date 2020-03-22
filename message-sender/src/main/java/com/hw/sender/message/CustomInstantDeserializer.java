package com.hw.sender.message;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

@Component
public class CustomInstantDeserializer extends JsonDeserializer<Instant> {

    private final DateTimeFormatter dateFormatter;

    public CustomInstantDeserializer(@Value("${dateformat}") String dateFormat) {
        dateFormatter = ofPattern(dateFormat);
    }

    @Override
    public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return ZonedDateTime.parse(p.getValueAsString(), dateFormatter).toInstant();
    }
}