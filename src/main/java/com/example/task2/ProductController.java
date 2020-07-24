package com.example.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "/api/v1", headers = "Accept=application/json")
public class ProductController
{
    @Autowired
    private ProductService service;

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/addProduct",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<ProductEntity> addProduct(@Valid ProductEntity productEntity)
    {
        return new ResponseEntity<>(service.postProduct(productEntity), HttpStatus.CREATED);
    }
}
