package com.hw.sender.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.Instant;

@Configuration
public class ProducerConfig {

    @Bean
    public Gson messageSerializer() {
        return new GsonBuilder()
                .registerTypeAdapter(Instant.class, (JsonSerializer<Instant>) (date, type, jsonSerializationContext) -> new JsonPrimitive(date.toEpochMilli())).create();
    }
}
