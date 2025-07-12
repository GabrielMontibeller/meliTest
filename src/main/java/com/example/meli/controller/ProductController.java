package com.example.meli.controller;

import com.example.meli.domain.model.Product;
import com.example.meli.dto.ProductDTO;
import com.example.meli.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {

        List<Product> product = service.getAllProducts();

        List<ProductDTO> productDto = product.stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getPrice()))
                .collect(Collectors.toList());

        log.info("{} Produtos Retornados", productDto.size());
        return ResponseEntity.ok(productDto);
    }
}
