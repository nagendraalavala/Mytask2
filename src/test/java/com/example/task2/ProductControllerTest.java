package com.example.task2;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest

{
    @Autowired
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
    public void createEmployeeAPI_success() throws Exception
    {
        mockEntity = new ProductEntity((long) 1,"Phone", 215.3, true);

        when(productService.postProduct(mockEntity)).thenReturn(mockEntity);

        String url = "/api/v1/addProduct";

        mvc.perform( MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                ;
                assertEquals(productService.postProduct(mockEntity), mockEntity);
    }



}
