package com.product.model;


import jakarta.persistence.*;
import lombok.*;


//@Data
//@AllArgsConstructor
//@NoArgsConstructor

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;

    private String productName;

    private String productDesc;

    private int productPrice;


}
