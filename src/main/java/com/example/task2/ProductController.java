package com.example.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
}
