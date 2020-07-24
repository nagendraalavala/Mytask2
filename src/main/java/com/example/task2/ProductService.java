package com.example.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService
{
    @Autowired
    private ProductRepo repo;

    public ProductEntity postProduct(ProductEntity productEntity)
    {
        return repo.save(productEntity);
    }
}
