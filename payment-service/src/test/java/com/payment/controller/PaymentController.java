package com.payment.controller;

import com.payment.PaymentService;
import com.payment.dto.PaymentRequestDTO;
import com.payment.dto.PaymentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/debit")
    public PaymentResponseDTO debit(@RequestBody PaymentRequestDTO requestDTO){
        return this.service.debit(requestDTO);
    }

    @PostMapping("/credit")
    public void credit(@RequestBody PaymentRequestDTO requestDTO){
        this.service.credit(requestDTO);
    }
}
