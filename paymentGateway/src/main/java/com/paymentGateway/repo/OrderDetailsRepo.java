package com.paymentGateway.repo;

import com.paymentGateway.model.Order;
import com.paymentGateway.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails,Integer> {

    OrderDetails findByOrderId(String orderId);
}
