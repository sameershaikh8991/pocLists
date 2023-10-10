package com.paymentGateway.controller;

import com.paymentGateway.model.Order;
import com.paymentGateway.model.OrderRequest;
import com.paymentGateway.model.Product;
import com.paymentGateway.repo.OrderRepository;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    @Value("${razorpay.api.key.id}")
    private String razorpayApiKey;


    @Value("${razorpay.api.secret.key}")
    private String razorpayApiSecretKey;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/create-payment")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {

        Product product =  new Product();
        product.setId(2L);
        product.setName("product 1");
        product.setPrice(1);

        double totalAmount = product.getPrice() * order.getQuantity();

        try {
            RazorpayClient razorpayClient = new RazorpayClient(razorpayApiKey, razorpayApiSecretKey);

            JSONObject options = new JSONObject();
            options.put("amount", totalAmount * 100);
            options.put("currency", "INR");
            options.put("receipt", "order_receipt_12345");

            com.razorpay.Order razorpayOrder = razorpayClient.orders.create(options);

            String razorpayOrderId = razorpayOrder.get("id");

            order.setProductId(product.getId());
            order.setRazorpayOrderId(razorpayOrderId);
            order.setTotalAmount(totalAmount);
            order.setPaymentStatus(razorpayOrder.get("status"));
            order.setLocalDateTime(LocalDateTime.now());

            Order savedOrder = orderRepository.save(order);

            return ResponseEntity.ok(savedOrder);
        } catch (RazorpayException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping("/make-payment")
    public ResponseEntity<Order> createOrders(@RequestBody OrderRequest orderRequest) {

        Product product =  new Product();
        product.setId(2L);
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

            Order order = new Order();
            order.setTotalAmount(totalAmount);
            order.setQuantity(orderRequest.getQuantity());
            order.setRazorpayOrderId(razorpayOrderId);
            order.setPaymentStatus(razorpayOrder.get("status"));

            System.out.println("order : "+order);

            return ResponseEntity.ok(order);
        } catch (RazorpayException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
