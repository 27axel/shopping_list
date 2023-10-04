package ru.sber.shopping_list.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.sber.shopping_list.model.Product;
import ru.sber.shopping_list.service.ProductService;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void getProducts_success() throws Exception {
        when(productService.getProducts()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/product/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getProductById_success() throws Exception {
        JSONObject productObject = new JSONObject();
        productObject.put("id", "1");
        productObject.put("name", "testName");
        productObject.put("cost", "200");

        mockMvc.perform(MockMvcRequestBuilders
                .get("/product/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService, times(1)).getProductById(any(Long.class));
    }

    @Test
    void saveProduct_success() throws Exception {
        JSONObject productObject = new JSONObject();
        productObject.put("id", "1");
        productObject.put("name", "testName");
        productObject.put("cost", "200");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/product/add")
                        .content(productObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService, times(1)).addProduct(any(Product.class));
    }

    @Test
    void updateProduct_success() throws Exception {
        JSONObject productObject = new JSONObject();
        productObject.put("id", "1");
        productObject.put("name", "testName");
        productObject.put("cost", "200");

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/product/{id}", 1)
                        .content(productObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService, times(1)).updateProduct(any(Long.class), any(Product.class));
    }

    @Test
    void deleteProduct_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/product/{id}", 1))
                .andExpect(status().isOk());

        verify(productService, times(1)).deleteProduct(any(Long.class));
    }
}
