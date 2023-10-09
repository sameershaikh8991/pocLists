package com.sell.service;


import com.sell.config.Config;
import com.sell.model.Sell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class SellProducer {

    public SellProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private RabbitTemplate rabbitTemplate;

    private static  final Logger LOGGER = LoggerFactory.getLogger(SellProducer.class);


    public void sendJsonProducer(Sell sell){
        LOGGER.info(String.format("message send  -->%s",sell.toString()));
        rabbitTemplate.convertAndSend(Config.JSON_EXCHANGE,Config.JSON_ROUTING_KEY,sell);
    }
}
