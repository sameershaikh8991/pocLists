package com.kafka.mode;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity

@Getter
@Setter
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String eventData;
}