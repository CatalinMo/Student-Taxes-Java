package com.student.taxes.security.config;

import com.student.taxes.security.domain.dto.TokenDetailsDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Configuration
public class TokenCacheConfig {

    @Bean
    public ConcurrentMap<String, TokenDetailsDto> tokens() {
        return new ConcurrentHashMap<>();
    }
}
