package com.example.meli.service.test;
import com.example.meli.domain.model.Discount;
import com.example.meli.domain.model.Product;
import com.example.meli.domain.repository.DiscountRepository;
import com.example.meli.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.example.meli.domain.repository.ProductRepository;


public class
ProductServiceTest {

    private ProductRepository productRepository;
    private DiscountRepository discountRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        discountRepository = mock(DiscountRepository.class);
        productService = new ProductService(productRepository, discountRepository);
    }

    @Test
    void findAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(
                new Product("1", "Notebook", 3500.0),
                new Product("2", "Smartphone", 2200.0)
        ));

        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
    }

    @Test
    void findProductById() {
        Product product = new Product("1", "Notebook", 3500.0);
        when(productRepository.findById("1")).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getById("1");
        assertTrue(result.isPresent());
        assertEquals("Notebook", result.get().getName());
    }

    @Test
    void findProductsFileDiscounts() {
        when(productRepository.findAll()).thenReturn(List.of(
                new Product("1", "Notebook", 1000.0)
        ));
        when(discountRepository.loadAll()).thenReturn(List.of(
                new Discount() {{ setProductId("1"); setDiscount(20.0); }}
        ));

        List<Product> result = productService.getProductsDiscountFromFile();
        assertEquals(800.0, result.get(0).getPrice(), 0.01);
    }


}
