package com.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SprinbootKafkaProducer implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SprinbootKafkaProducer.class);
    }

    @Autowired
    private KafkaProducer wikimediaChangesProducer;

    @Override
    public void run(String... args) throws Exception {
        wikimediaChangesProducer.sendMessage();
    }

}