package com.example.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService
{
    @Autowired
    private ProductRepo repo;



    //add
    public ProductEntity postProduct(ProductEntity productEntity)
    {
        return repo.save(productEntity);
    }

    //getallProducts
    public List<ProductEntity> getAllProducts()
    {
        return repo.findAll();
    }

    //getProductById
    public Optional<ProductEntity> getProductById(Long id)
    {
        return repo.findById(id);

    }

    //updateProduct
    public Object updateProduct(Long id, ProductEntity newProduct)
    {
        Optional<ProductEntity> product = repo.findById(id);
        ProductEntity oldProduct = product.get();
        if (product.isPresent())
        {
            oldProduct.setName(newProduct.getName());
            oldProduct.setCost(newProduct.getCost());

            return oldProduct;
        }
        else
            return new RuntimeException("Product Id not Found");
    }

    //delete
    public void
    deleteProduct(Long id)
    {
        repo.deleteById(id);

    }

}
