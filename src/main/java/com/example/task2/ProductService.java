package com.example.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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
    private AdminPOJO pojo;
    private ProductEntity productEntity;

    public ProductService(ProductRepo repo) {
        this.repo = repo;
        this.restTemplate = new RestTemplate();
        this.pojo = new AdminPOJO();
        this.productEntity = new ProductEntity();
    }

    //add
    public ProductEntity postProduct(ProductEntity productEntity)
    {
        return repo.save(productEntity);

//        String url = "http://localhost:9090/api/v1/admin/1";
//        ResponseEntity<AdminPOJO> getAdmin = restTemplate.exchange(url, HttpMethod.GET,null,AdminPOJO.class);
//        AdminPOJO response = getAdmin.getBody();
//        System.out.println(response.getAdminStatus());
//        if(response.getAdminStatus().equalsIgnoreCase("Active"))
//        {
//
//
//        }
//        else
//        {
//            throw new RuntimeException("Cannot be posted. The status of Admin is not active");
//        }


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
