package com.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReverseInventory {

    @Autowired
    private InvRepository repository;

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    @KafkaListener(topics = "reversed-inventory", groupId = "inventory-group")
    public void reverseInventory(String event) {

        try {

            InventoryEvent inventoryEvent = new ObjectMapper().readValue(event, InventoryEvent.class);

            Iterable<Inventory> inv = this.repository.findByItem(inventoryEvent.getOrder().getItem());

            inv.forEach(i -> {

                i.setQuantity(i.getQuantity() + inventoryEvent.getOrder().getQuantity());

                this.repository.save(i);
            });

            PaymentEvent paymentEvent = new PaymentEvent();
            paymentEvent.setOrder(inventoryEvent.getOrder());
            paymentEvent.setType("PAYMENT_REVERSED");
            this.kafkaTemplate.send("reversed-payments", paymentEvent);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
