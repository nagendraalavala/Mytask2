package com.example.task2;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService
{

    private ProductRepo repo;
    private RestTemplate restTemplate;
    private ProductEntity productEntity;

    public ProductService(ProductRepo repo) {
        this.repo = repo;
        this.restTemplate = new RestTemplate();
        this.productEntity = new ProductEntity();
    }

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
    public ResponseEntity<ProductEntity> getProductById(Long id) {
        Optional<ProductEntity> product = repo.findById(id);
//        ProductEntity getProduct = product.get();

        if (product.isPresent())
        {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        }
        else
        {
            //throw new ProductNotFoundException(" Product Not Found");
            return new ResponseEntity(new RuntimeException("No id Found"),HttpStatus.NOT_FOUND);
        }

    }

    //updateProduct
    public Object updateProduct(Long id, ProductEntity newProduct)
    {
        Optional<ProductEntity> product = repo.findById(id);
        ProductEntity oldProduct = product.get();
        if (product.isPresent())
        {
            oldProduct.setId(newProduct.getId());
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


