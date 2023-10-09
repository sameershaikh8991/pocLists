package com.buy.service;

import com.buy.config.Config;
import com.buy.model.Buy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private RabbitTemplate rabbitTemplate;

    private static  final Logger LOGGER = LoggerFactory.getLogger(Producer.class);


    public void sendJsonProducer(Buy buy){
        LOGGER.info(String.format("message send  -->%s",buy.toString()));
        rabbitTemplate.convertAndSend(Config.JSON_EXCHANGE,Config.JSON_ROUTING_KEY,buy);
    }
}
