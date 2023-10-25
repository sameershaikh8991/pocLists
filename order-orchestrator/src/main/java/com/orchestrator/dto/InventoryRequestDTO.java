package com.orchestrator.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequestDTO {

    private Integer userId;
    private Integer productId;
    private UUID orderId;

}
