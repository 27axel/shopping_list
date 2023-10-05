package ru.sber.shopping_list.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.sber.shopping_list.model.Product;
import ru.sber.shopping_list.repository.ProductRepository;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    Product productTest = new Product();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productTest.setId(1);
        productTest.setName("testName");
        productTest.setCost(200);
    }

    @Test
    void addProductTest() {
        when(productService.addProduct(productTest)).thenReturn(productTest);

        Product result = productService.addProduct(productTest);
        assertEquals(productTest, result);
        verify(productRepository, times(1)).save(productTest);
    }

    @Test
    void getAllProductTest() {
        List<Product> productList = List.of(productTest);
        when(productRepository.findAll()).thenReturn(productList);

        Collection<Product> result = productService.getProducts();
        assertEquals(productList, result);
        verify(productRepository, times(1)).findAll();
    }
}
