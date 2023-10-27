package com.product.service;

import com.product.dto.Event;
import com.product.model.Product;
import com.product.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class ProductService {


    @Autowired
    private ProductRepository repository;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public Product createProduct(Event productEvent){
        Product productDO = repository.save(productEvent.getProduct());
        Event event=new Event("create", productDO);
        kafkaTemplate.send("product-topic", event);
        return productDO;
    }

    public Product updateProduct(long id,Event productEvent){
        Product existingProduct = repository.findById(id).get();
        Product newProduct=productEvent.getProduct();
        existingProduct.setName(newProduct.getName());
        existingProduct.setPrice(newProduct.getPrice());
        existingProduct.setDescription(newProduct.getDescription());
        Product productDO = repository.save(existingProduct);
        Event event=new Event("update", productDO);
        kafkaTemplate.send("product-topic", event);
        return productDO;
    }

}
