package com.paymentGateway.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {
    private Long productId;
    private int quantity;
    private BigDecimal amount;
}
