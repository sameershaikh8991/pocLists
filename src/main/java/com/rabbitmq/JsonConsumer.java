package com.rabbitmq;


import com.rabbitmq.config.Config;
import com.rabbitmq.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class JsonConsumer {
    private static  final Logger LOGGER = LoggerFactory.getLogger(JsonProducer.class);

    @RabbitListener(queues = Config.JSON_QUEUE)
    public void consumeMessage(User user){

        System.out.println("inside consumeMessage ");
    LOGGER.info(String.format("message received -->%s",user.toString()));
    }
}
