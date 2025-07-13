package com.example.meli.domain.repository;

import com.example.meli.domain.model.Discount;
import com.example.meli.domain.model.Product;

import java.util.List;

public interface DiscountRepository {
    List<Discount> loadAll();
}
