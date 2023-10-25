package com.order.service;

import com.order.dto.*;
import com.order.entity.PurchaseOrder;
import com.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.Map;

@Service
public class OrderService {


    private static final Map<Integer, Double> PRODUCT_PRICE =  Map.of(
            1, 100d,
            2, 200d,
            3, 300d
    );

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private Sinks.Many<OrchestratorRequestDTO> sink;


    //TODO:The Sinks class provides different types of sinks for reactive programming in Spring,
    // allowing you to send data to multiple subscribers in a reactive and non-blocking manner
    // Sinks.Many : ->  sink for multicasting elements, meaning you can push data to multiple subscribers.
    // This is useful when you want to broadcast data to multiple consumers, which is a common scenario in reactive programming.

    public Mono<PurchaseOrder> createOrder(OrderRequestDTO orderRequestDTO){
        return this.orderRepo.save(dtoToEntity(orderRequestDTO))
                .doOnNext(e -> orderRequestDTO.setOrderId(e.getId()))
                .doOnNext(e -> this.emitEvent(orderRequestDTO));
    }
    public Flux<OrderResponseDTO> getAll() {
        return orderRepo.findAll().map(this::entityToDto);
    }
    private void emitEvent(OrderRequestDTO orderRequestDTO){
        this.sink.tryEmitNext(this.getOrchestratorRequestDTO(orderRequestDTO));
    }
    public Mono<Void> updateOrder(final OrchestratorResponseDTO responseDTO){
        return this.orderRepo.findById(responseDTO.getOrderId())
                .doOnNext(p -> p.setStatus(responseDTO.getStatus()))
                .flatMap(this.orderRepo::save)
                .then();

    }



    private PurchaseOrder dtoToEntity(final OrderRequestDTO dto){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(dto.getOrderId());
        purchaseOrder.setProductId(dto.getProductId());
        purchaseOrder.setUserId(dto.getUserId());
        purchaseOrder.setStatus(OrderStatus.ORDER_CREATED);
        purchaseOrder.setPrice(PRODUCT_PRICE.get(purchaseOrder.getProductId()));
        return purchaseOrder;
    }

    private OrderResponseDTO entityToDto(final PurchaseOrder purchaseOrder){
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(purchaseOrder.getId());
        dto.setProductId(purchaseOrder.getProductId());
        dto.setUserId(purchaseOrder.getUserId());
        dto.setStatus(purchaseOrder.getStatus());
        dto.setAmount(purchaseOrder.getPrice());
        return dto;
    }

    public OrchestratorRequestDTO getOrchestratorRequestDTO(OrderRequestDTO orderRequestDTO){
        OrchestratorRequestDTO requestDTO = new OrchestratorRequestDTO();
        requestDTO.setUserId(orderRequestDTO.getUserId());
        requestDTO.setAmount(PRODUCT_PRICE.get(orderRequestDTO.getProductId()));
        requestDTO.setOrderId(orderRequestDTO.getOrderId());
        requestDTO.setProductId(orderRequestDTO.getProductId());
        return requestDTO;
    }
}
