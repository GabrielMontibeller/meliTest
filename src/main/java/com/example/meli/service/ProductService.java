package com.example.meli.service;

import com.example.meli.domain.model.Product;
import com.example.meli.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository repository;

    public List<Product> getAllProducts() {
        log.info("Buscando todos os produtos...");
        return repository.findAll();
    }
}
