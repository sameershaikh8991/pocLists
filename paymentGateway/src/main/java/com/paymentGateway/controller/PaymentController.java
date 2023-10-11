package com.paymentGateway.controller;

import com.paymentGateway.model.*;
import com.paymentGateway.repo.OrderDetailsRepo;
import com.paymentGateway.repo.OrderRepository;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Refund;
import jakarta.websocket.server.PathParam;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    @Value("${razorpay.api.key.id}")
    private String razorpayApiKey;

    @Value("${razorpay.api.secret.key}")
    private String razorpayApiSecretKey;


    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @PostMapping("/create-payment")
    public ResponseEntity<OrderDetails> createOrder(@RequestBody OrderRequest orderRequest) {



        BigDecimal totalAmount = orderRequest.getAmount();
        try {

            RazorpayClient razorpayClient = new RazorpayClient(razorpayApiKey, razorpayApiSecretKey);
            JSONObject options = new JSONObject();
            options.put("amount", totalAmount.multiply(BigDecimal.valueOf(100)));
            options.put("currency", "INR");
            options.put("receipt", "order_receipt_12345");

            com.razorpay.Order razorpayOrder = razorpayClient.orders.create(options);

            System.out.println(razorpayOrder.get("id").toString());
            String razorpayOrderId = razorpayOrder.get("id");

            OrderDetails orderDetails =  new OrderDetails();
            orderDetails.setAmount(razorpayOrder.get("amount"));
            orderDetails.setOrderId(razorpayOrderId);
            orderDetails.setStatus(razorpayOrder.get("status"));
//            orderDetailsRepo.save(orderDetails);
            System.out.println("order : " + orderDetails);

            return ResponseEntity.ok(orderDetails);
        } catch (RazorpayException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/make-payment")
    public ResponseEntity<OrderDetails> createOrders(@RequestBody OrderRequest orderRequest) {

        Product product = new Product();
        product.setId(2);
        product.setName("product 1");
        product.setPrice(1);

        double totalAmount = product.getPrice() * orderRequest.getQuantity();
        try {

            RazorpayClient razorpayClient = new RazorpayClient(razorpayApiKey, razorpayApiSecretKey);

            JSONObject options = new JSONObject();
            options.put("amount", totalAmount * 100);
            options.put("currency", "INR");
            options.put("receipt", "order_receipt_12345");

            com.razorpay.Order razorpayOrder = razorpayClient.orders.create(options);

            System.out.println(razorpayOrder.get("id").toString());
            String razorpayOrderId = razorpayOrder.get("id");

            OrderDetails orderDetails =  new OrderDetails();
            orderDetails.setAmount(razorpayOrder.get("amount"));
            orderDetails.setOrderId(razorpayOrderId);
            orderDetails.setStatus(razorpayOrder.get("status"));
            System.out.println("order : " + orderDetails);

//            orderDetailsRepo.save(orderDetails);

            return ResponseEntity.ok(orderDetails);
        } catch (RazorpayException e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping("/refund-amount")
    public ResponseEntity<Refund> makeRefund(@PathParam("paymentId") String paymentId) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(razorpayApiKey, razorpayApiSecretKey);

        JSONObject refundRequest = new JSONObject();
        refundRequest.put("speed", "instant");
        JSONObject notes = new JSONObject();
        notes.put("notes_key_1", "Tea, Earl Grey, Hot");
        refundRequest.put("notes", notes);

        Refund refund = razorpay.payments.refund(paymentId, null);
        return ResponseEntity.ok(refund);
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateStatus(@RequestBody UpdateRequest updateRequest){
        OrderDetails orderDetails = orderDetailsRepo.findByOrderId(updateRequest.getOrderId());

        if(orderDetails==null){
            throw new RuntimeException("payment details not found");
        }

        orderDetails.setPaymentId(updateRequest.getPaymentId());
        orderDetails.setStatus(updateRequest.getStatus());
        orderDetailsRepo.save(orderDetails);

        return ResponseEntity.ok("done");

    }

}
