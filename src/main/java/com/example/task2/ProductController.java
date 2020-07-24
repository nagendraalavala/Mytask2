package com.example.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class ProductController
{

    @Autowired
    private ProductService service;

    @PostMapping("/addProduct")
    public ResponseEntity<ProductEntity> addProduct(@RequestBody ProductEntity productEntity)
    {
        return new ResponseEntity<>(service.postProduct(productEntity), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ProductEntity>> getAllProducts()
    {
        return new ResponseEntity<>(service.getAllProducts(),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductEntity> updateproduct(@PathVariable Long id, @RequestBody ProductEntity productEntity)
    {
        return new ResponseEntity(service.updateProduct(id,productEntity),HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id)
    {
        service.deleteProduct(id);
    }
}
