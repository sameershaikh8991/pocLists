package com.product.service;

import com.product.Exception.NotFoundException;
import com.product.model.Product;
import com.product.repo.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProducrService {

 private ProductRepo productRepo;


    @Override
    public Product saveProduct(Product product) {
        System.out.println("saving service....");
        return  productRepo.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public Product updateProduct(Product product) {
        Product existingProduct = productRepo.findById(product.getProductId()).get();

        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductDesc(product.getProductDesc());
        existingProduct.setProductPrice(product.getProductPrice());
        Product updatedProduct = productRepo.save(existingProduct);
        return updatedProduct;
    }

    @Override
    public void deleteProduct(int pid) {
        productRepo.deleteById(pid);
    }

    @Override
    public Product getProductById(int userId) throws Exception {
        Optional<Product> optionalUser = productRepo.findById(userId);
        if(optionalUser.isEmpty()){
            throw new NotFoundException("product not found");
        }
        return optionalUser.get();
    }


}
