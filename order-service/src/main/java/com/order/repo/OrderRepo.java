package com.order.repo;

import com.order.entity.PurchaseOrder;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface OrderRepo extends ReactiveCrudRepository<PurchaseOrder, UUID> {
}
