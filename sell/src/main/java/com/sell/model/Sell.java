package com.sell.model;


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
    private int qty;
    private BigDecimal price;
    private String symbol;
    
}
