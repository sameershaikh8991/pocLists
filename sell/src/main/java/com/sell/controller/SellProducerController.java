package com.sell.controller;


import com.sell.model.Sell;
import com.sell.service.SellProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sell")
public class SellProducerController {

    @Autowired
    private SellProducer sellProducer;


    @PostMapping("/send")
    public ResponseEntity<String> sendJsonMessage(@RequestBody Sell sellData){
        System.out.println("sellData :"+sellData);

        Sell sell =  new Sell(sellData.getId(),sellData.getQty(),sellData.getPrice(),sellData.getSymbol());

        System.out.println("sell :"+sell);
        sellProducer.sendJsonProducer(sell);
        return ResponseEntity.ok("message send");

    }

}
