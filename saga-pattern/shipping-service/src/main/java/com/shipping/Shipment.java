package com.shipping;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Shipment {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String address;


    @Column
    private String status;


    @Column
    private long orderId;
}
