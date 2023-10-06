package com.rabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {



    public static final String QUEUE = "sam-queue";
    public static final String EXCHANGE = "exchange";
    public static final String ROUTING_KEY = "routingKey";

    public static final String JSON_QUEUE = "json-queue";
    public static final String JSON_EXCHANGE = "json-exchange";
    public static final String JSON_ROUTING_KEY = "json-routingKey";

//    @Bean
//    public Queue queue(){
//        return  new Queue(QUEUE);
//    }
//
//    @Bean
//    public TopicExchange exchange(){
//        return  new TopicExchange(EXCHANGE);
//    }
//
//    @Bean
//    public Binding binding(){
//        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY);
//    }


//    json data

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
