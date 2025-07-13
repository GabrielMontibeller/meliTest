package com.example.meli.service;

import com.example.meli.domain.model.Discount;
import com.example.meli.domain.model.Product;
import com.example.meli.domain.repository.DiscountRepository;
import com.example.meli.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository repository;
    private final DiscountRepository discountRepository;

    public List<Product> getAllProducts() {
        log.info("Buscando todos os produtos...");
        return repository.findAll();
    }

    public Optional<Product> getById(String id) {
        log.info("Buscando ID" + " " + id);
        return repository.findById(id);
    }

    public List<Product> getProductsDiscountUrl(double percent) {
        return repository.findAll().stream()
                .map(p -> new Product(
                        p.getId(),
                        p.getName(),
                        p.getPrice() * (1 - percent / 100)))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsDiscountFromFile() {
        List<Discount> rules = discountRepository.loadAll();
        return repository.findAll().stream()
                .map(product -> {
                    Optional<Discount> rule = rules.stream()
                            .filter(r -> r.getProductId().equals(product.getId()))
                            .findFirst();
                    double discount = rule.map(Discount::getDiscount).orElse(0.0);
                    double discountedPrice = product.getPrice() * (1 - discount / 100);
                    return new Product(product.getId(), product.getName(), discountedPrice);
                })
                .collect(Collectors.toList());
    }

}
