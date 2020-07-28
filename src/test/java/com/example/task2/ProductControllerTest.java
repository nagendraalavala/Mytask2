package com.example.task2;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest

{
    private MockMvc mvc;

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private ProductEntity mockEntity;

    @Before
    public void setup(){

        mvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void createProductAPI_success() throws Exception
    {
        mockEntity = new ProductEntity((long) 1,"Phone", 215.3, "Active");

        when(productService.postProduct(mockEntity)).thenReturn(mockEntity);

        String url = "/api/v1/addProduct";

        mvc.perform( MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE));
                assertEquals(productService.postProduct(mockEntity), mockEntity);
    }


   @Test
    public void get_all_products_test_success() throws Exception
   {
       List<ProductEntity> products = Arrays.asList(
               new ProductEntity((long) 1,"Phone",250.6,"Active"),
               new ProductEntity((long) 2,"Laptop",300.9,"Active"));

       when(productService.getAllProducts()).thenReturn(products);

       String url = "/api/v1/";

       mvc.perform(MockMvcRequestBuilders.get(url)
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$",hasSize(2)));

       assertEquals(productService.getAllProducts(), products);
    }


    @Test
    public void get_product_by_id_test_success() throws Exception
    {
        Optional<ProductEntity> productEntity = Optional.of(new ProductEntity((long) 1, "Phone", 363.9, "Active"));
        ProductEntity product = productEntity.get();

        when(productService.getProductById(product.getId())).thenReturn(productEntity);

        String url= "/api/v1/{id}";

        mvc.perform(MockMvcRequestBuilders.get(url,1)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());

        verify(productService, times(1)).getProductById(product.getId());
        assertEquals(productService.getProductById(product.getId()),productEntity);


    }

    @Test
    public void update_product_by_id_Success() throws Exception
    {
        ProductEntity productEntity = new ProductEntity((long) 1,"Phone",300.0,"Active");
        ProductEntity newProduct = new ProductEntity((long) 1 ,"Laptop",323.3,"Active");

        when(productService.updateProduct((long) 1,productEntity)).thenReturn(newProduct);

        String url = "/api/v1/update/{id}";

        mvc.perform(MockMvcRequestBuilders.put(url)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())

        ;

        assertEquals(productService.updateProduct((long) 1,newProduct),newProduct);

    }

    @Test
    public void deleteEmployeeAPI() throws Exception
    {

        mvc.perform( MockMvcRequestBuilders.delete("/api/v1/delete/{id}", 1) );
    }



}
