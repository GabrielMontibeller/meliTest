package com.example.meli.service.test;
import com.example.meli.domain.model.Product;
import com.example.meli.service.ProductService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.example.meli.domain.repository.ProductRepository;


public class ProductServiceTest {

    @Test
    void shouldReturnProductList() {
        ProductRepository mockRepo = mock(ProductRepository.class);
        when(mockRepo.findAll()).thenReturn(List.of(new Product("1", "TV", 1999.0)));

        ProductService service = new ProductService(mockRepo);
        List<Product> result = service.getAllProducts();

        assertEquals(1, result.size());
        assertEquals("TV", result.get(0).getName());
    }

}
