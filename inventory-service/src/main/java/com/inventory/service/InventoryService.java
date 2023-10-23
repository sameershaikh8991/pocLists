package com.inventory.service;


import com.inventory.dto.InventoryRequestDTO;
import com.inventory.dto.InventoryResponseDTO;
import com.inventory.dto.InventoryStatus;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {


    private Map<Integer, Integer> invmap = new HashMap<>();

    @PostConstruct
    public void init() {
        invmap.put(1, 2);
        invmap.put(2, 50);
        invmap.put(4, 60);
    }

    public InventoryResponseDTO deductInventory(InventoryRequestDTO requestDTO) {
        int quantity = invmap.getOrDefault(requestDTO.getProductId(), 0);

        InventoryResponseDTO responseDTO = new InventoryResponseDTO();
        responseDTO.setOrderId(requestDTO.getOrderId());
        responseDTO.setUserId(requestDTO.getUserId());
        responseDTO.setProductId(requestDTO.getProductId());
        responseDTO.setStatus(InventoryStatus.UNAVAILABLE);
        if(quantity > 0){
            responseDTO.setStatus(InventoryStatus.AVAILABLE);
            this.invmap.put(requestDTO.getProductId(), quantity - 1);
        }
        return responseDTO;
    }

    public void addInventory(final InventoryRequestDTO requestDTO){
        this.invmap
                .computeIfPresent(requestDTO.getProductId(), (k, v) -> v + 1);
    }
}
