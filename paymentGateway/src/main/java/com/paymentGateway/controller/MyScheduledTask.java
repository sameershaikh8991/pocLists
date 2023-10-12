package com.paymentGateway.controller;

import com.paymentGateway.model.OrderDetails;
import com.paymentGateway.repo.OrderDetailsRepo;
import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyScheduledTask {

    @Value("${razorpay.api.key.id}")
    private String razorpayApiKey;

    @Value("${razorpay.api.secret.key}")
    private String razorpayApiSecretKey;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;


    @Scheduled(fixedRate = 20000)
    public void myScheduledMethod() throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(razorpayApiKey, razorpayApiSecretKey);


//        String orderId ="order_MnHBvnupauSlS3";

        List<OrderDetails> all = orderDetailsRepo.findAll();

        for (OrderDetails orderDetail : all) {
            String orderId1 = orderDetail.getOrderId();
            OrderDetails orderDetails = orderDetailsRepo.findByOrderId(orderId1);

            Order fetch = razorpay.orders.fetch(orderId1);

            int attempts= fetch.get("attempts");
            System.out.println("attempts"+attempts);
            orderDetails.setStatus(fetch.get("status"));
            orderDetailsRepo.save(orderDetails);
        }


    }
}
