package com.apachekafka.service;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafKaConsumer{

    private static final Logger LOGGER = LoggerFactory.getLogger(KafKaConsumer.class);

    @KafkaListener(topics = "topic",
            groupId = "group-id")
    public void consume(String message){
        LOGGER.info(String.format("Message received -> %s", message));
    }
}
