package com.matching.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sell {

    private int id;
    private Long orderId;
    private int originalQty;
    private int executedQty;
    private  int orderQty;
    private BigDecimal price;
    private String symbol;
    
}
