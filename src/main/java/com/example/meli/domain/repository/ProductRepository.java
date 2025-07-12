package com.example.meli.domain.repository;

import com.example.meli.domain.model.Product;
import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
}
