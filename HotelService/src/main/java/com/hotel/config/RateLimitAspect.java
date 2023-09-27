package com.hotel.config;

//import com.google.common.util.concurrent.RateLimiter;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class RateLimitAspect {
//
//    private final RateLimiter rateLimiter;
//
//    @Autowired
//    public RateLimitAspect(RateLimiter rateLimiter) {
//        this.rateLimiter = rateLimiter;
//    }
//
//    @Before("@annotation(RateLimit)") // Annotate your API methods with @RateLimit
//    public void rateLimit() {
//        if (!rateLimiter.tryAcquire()) {
//            throw new RuntimeException("API rate limit exceeded");
//        }
//    }
//}


import com.google.common.util.concurrent.RateLimiter;
import com.hotel.exception.RateLimitException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@Aspect
@Component
public class RateLimitAspect {

    private final Map<String, RateLimiter> rateLimiters;

    @Autowired
    public RateLimitAspect(Map<String, RateLimiter> rateLimiters) {
        this.rateLimiters = rateLimiters;
    }

    @Autowired
    private HttpServletRequest request;

    @Before("@annotation(RateLimit)") // Annotate your API methods with @RateLimit
    public void rateLimit() throws UnknownHostException {
        String clientIP = getClientIP();
        RateLimiter rateLimiter = rateLimiters.computeIfAbsent(clientIP, k -> RateLimiter.create(2.0)); // 2 requests per minute

        if (!rateLimiter.tryAcquire()) {
            throw new RateLimitException("API rate limit exceeded for IP: " + clientIP);
        }
    }

    private String getClientIP() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("My IP Address: " + localHost.getHostAddress());

        String hostAddress = localHost.getHostAddress();

        return hostAddress;
    }
}


