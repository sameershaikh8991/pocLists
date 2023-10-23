package com.storage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    @Autowired
    private InvRepository repository;

    @Autowired
    private KafkaTemplate<String, InventoryEvent> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaPaymentTemplate;

    @KafkaListener(topics = "new-payments", groupId = "payments-group")
    public void updateInventory(String paymentEvent) throws JsonMappingException, JsonProcessingException {

        InventoryEvent event = new InventoryEvent();

        PaymentEvent p = new ObjectMapper().readValue(paymentEvent, PaymentEvent.class);
        OrderRequest order = p.getOrder();

        try {
            Iterable<Inventory> inventories = this.repository.findByItem(order.getItem());
            boolean exists = inventories.iterator().hasNext();

            if (!exists)
                throw new Exception("Stock not available");

            inventories.forEach(
                    i -> {
                        i.setQuantity(i.getQuantity() - order.getQuantity());

                        this.repository.save(i);
                    });

            event.setType("INVENTORY_UPDATED");
            event.setOrder(p.getOrder());
            this.kafkaTemplate.send("new-inventory", event);

        } catch (Exception e) {

            PaymentEvent pe = new PaymentEvent();
            pe.setOrder(order);
            pe.setType("PAYMENT_REVERSED");
            this.kafkaPaymentTemplate.send("reversed-payments", pe);

        }

    }

    @PostMapping("/inventory")
    public void addInventory(@RequestBody Storage storage) {

        Iterable<Inventory> items = this.repository.findByItem(storage.getItem());

        if (items.iterator().hasNext()) {

            items.forEach(i -> {

                i.setQuantity(storage.getQuantity() + i.getQuantity());
                this.repository.save(i);
            });
        } else {

            Inventory i = new Inventory();
            i.setItem(storage.getItem());
            i.setQuantity(storage.getQuantity());
            this.repository.save(i);
        }
    }

}

