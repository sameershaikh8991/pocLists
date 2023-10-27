package com.product.service;


import com.product.dto.Event;
import com.product.model.Product;
import com.product.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getProducts() {
        return repository.findAll();
    }

    @KafkaListener(topics = "product-topic", groupId = "product-group")
    public void processProductEvents(Event productEvent) {
        Product product = productEvent.getProduct();
        product.setPid(productEvent.getProduct().getId());
        if (productEvent.getEventType().equals("create")) {
            repository.save(product);
        }
        if (productEvent.getEventType().equals("update")) {
            Product existingProduct = repository.findByPid(product.getId()).get();
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDescription(product.getDescription());
            repository.save(existingProduct);
        }
    }
}