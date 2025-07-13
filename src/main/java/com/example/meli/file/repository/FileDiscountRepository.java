package com.example.meli.file.repository;

import com.example.meli.domain.model.Discount;
import com.example.meli.domain.model.Product;
import com.example.meli.domain.repository.DiscountRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileDiscountRepository implements DiscountRepository {

    private final ObjectMapper mapper;

    @Override
    public List<Discount> loadAll() {
        try (InputStream input = getClass().getResourceAsStream("/discounts.json")) {
            return mapper.readValue(input, new TypeReference<>() {});
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
