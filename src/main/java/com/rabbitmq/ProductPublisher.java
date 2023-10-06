package com.rabbitmq;


import com.rabbitmq.config.Config;
import com.rabbitmq.model.Product;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductPublisher {


    @Autowired
    private RabbitTemplate rabbitTemplate;
    @PostMapping("/send")
    public String orderProduct(@RequestBody Product product ){

        product.setId(UUID.randomUUID());
        ProductResponse  productResponse =  new ProductResponse(product,"PROCESS","DONE");


        rabbitTemplate.convertAndSend(Config.EXCHANGE,Config.ROUTING_KEY,productResponse);


        return "success";
    }
}
