package com.order.entity;

import com.order.dto.OrderStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;


import java.util.UUID;

@Data

public class PurchaseOrder {

    @Id
    private UUID id;
    private Integer userId;
    private Integer productId;
    private Double price;
    private OrderStatus status;

}
