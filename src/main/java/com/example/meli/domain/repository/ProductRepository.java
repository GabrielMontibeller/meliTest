package com.example.meli.domain.repository;

import com.example.meli.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(String id);
}
