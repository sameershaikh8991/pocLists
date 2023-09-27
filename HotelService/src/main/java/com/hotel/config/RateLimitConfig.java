package com.hotel.config;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class RateLimitConfig {
//    @Bean
//    public RateLimiter rateLimiter() {
//        double permitsPerMinute = 2.0;
//        double permitsPerSecond = permitsPerMinute / 60.0;
//        return RateLimiter.create(permitsPerSecond);
//    }

    @Bean
    public Map<String, RateLimiter> rateLimiters() {
        return new ConcurrentHashMap<>();
    }
}

