package com.product.service;

import com.product.model.Product;

import java.util.List;

public interface ProducrService {

    Product saveProduct(Product product);

    List<Product> getAllProduct();

    Product updateProduct(Product user);

    void deleteProduct(int pid);

    Product getProductById(int userId) throws Exception;

}
