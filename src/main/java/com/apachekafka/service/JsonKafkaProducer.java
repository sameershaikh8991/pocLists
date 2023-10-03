package com.apachekafka.service;

import com.apachekafka.model.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service

public class JsonKafkaProducer {

    private KafkaTemplate<String, User> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void  sendMessage(User user){

        Message<User> message = MessageBuilder.withPayload(user)
                .setHeader(KafkaHeaders.TOPIC,"topic")
                .build();

        kafkaTemplate.send(message);

        kafkaTemplate.send("partitions-topic","1",user);

    }
}
