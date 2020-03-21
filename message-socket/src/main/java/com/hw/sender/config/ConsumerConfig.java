package com.hw.sender.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class ConsumerConfig {

    @Bean
    public Gson messageDeserializer() {
        return new Gson();
    }
}
