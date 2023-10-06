package com.rabbitmq;


import com.rabbitmq.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductResponse {

    private Product product;
    private String status;
    private String message;


}
