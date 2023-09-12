package com.product.controller;

import com.product.model.Product;
import com.product.service.ProducrService;
import com.product.service.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
@CrossOrigin("*")
public class ProductController {


   private ProducrService productService;


    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getUserById(@PathVariable("id") int pid) throws Exception {
        Product product = productService.getProductById(pid);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> users = productService.getAllProduct();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") int pid,
                                           @RequestBody Product product){

        product.setProductId(pid);
        Product UpdateProduct = productService.updateProduct(product);
        return new ResponseEntity<>(UpdateProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int pid){
        productService.deleteProduct(pid);
        return new ResponseEntity<>("Product successfully deleted!", HttpStatus.OK);
    }



}
