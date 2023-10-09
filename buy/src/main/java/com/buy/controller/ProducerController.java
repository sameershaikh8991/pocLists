package com.buy.controller;

import com.buy.model.Buy;
import com.buy.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("api/v1/buy")
public class ProducerController {

    @Autowired
    private Producer producer;

    public ProducerController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendJsonMessage(@RequestBody Buy data){
        System.out.println("user :"+data);
        Random random=new Random();
        data.setOrderId((long) random.nextInt());

        System.out.println("buy :"+data);
        producer.sendJsonProducer(data);
        return ResponseEntity.ok("message buy send");

    }

}
