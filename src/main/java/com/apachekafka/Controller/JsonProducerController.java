package com.apachekafka.Controller;


import com.apachekafka.model.User;
import com.apachekafka.service.JsonKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/json")
public class JsonProducerController {

    @Autowired
    private JsonKafkaProducer jsonKafkaProducer;


@PostMapping("/send")
@Scheduled(fixedRate = 30000)
    public String sendJsonMessage(@RequestBody User user){

        jsonKafkaProducer.sendMessage(user);
        return "message send";
    }

}
