package com.rabbitmq;

import com.rabbitmq.config.Config;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

@RabbitListener(queues = Config.QUEUE)
    public void consumeMessage(ProductResponse productResponse){
        System.out.println("message ->"+productResponse);
    }
}
