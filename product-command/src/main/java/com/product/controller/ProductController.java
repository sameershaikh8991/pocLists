package com.product.controller;


import com.product.dto.Event;
import com.product.model.Product;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService commandService;

    @PostMapping
    public Product createProduct(@RequestBody Event productEvent) {
        return commandService.createProduct(productEvent);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Event productEvent) {
        return commandService.updateProduct(id, productEvent);
    }
}