package com.buy.config;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.*;

@Configuration
public class Config {

    public static final String JSON_QUEUE = "buy-queue";

    public static final String JSON_EXCHANGE = "buy-exchange";

    public static final String JSON_ROUTING_KEY = "buy-routingKey";

    @Bean
    public Queue jsonQueue(){
        return  new Queue(JSON_QUEUE);
    }

    @Bean
    public TopicExchange jsonExchange(){
        return  new TopicExchange(JSON_EXCHANGE);
    }

    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonQueue()).to(jsonExchange()).with(JSON_ROUTING_KEY);
    }


    @Bean
    public MessageConverter messageConverter(){
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){

        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }



}
