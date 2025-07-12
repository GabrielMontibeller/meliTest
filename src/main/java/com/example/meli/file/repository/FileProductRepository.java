package com.example.meli.file.repository;

import com.example.meli.domain.model.Product;
import com.example.meli.domain.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileProductRepository implements ProductRepository {

    private final ObjectMapper mapper;

    @Override
    public List<Product> findAll() {
        try (InputStream input = getClass().getResourceAsStream("/products.json")) {
            return mapper.readValue(input, new TypeReference<>() {});
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
