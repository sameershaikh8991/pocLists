package com.order;

import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {

    private String item;

    private int quantity;

    private double amount;

    private String paymentMode;

    private long orderId;

    private String address;
}
