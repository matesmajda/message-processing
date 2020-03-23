package com.hw.query.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {

    public static final String PALINDROME_CACHE = "palindromes";

}