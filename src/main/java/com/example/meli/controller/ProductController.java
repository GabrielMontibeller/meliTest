package com.example.meli.controller;

import com.example.meli.domain.ProductNotFoundException;
import com.example.meli.domain.model.Product;
import com.example.meli.dto.ProductDTO;
import com.example.meli.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable String id) {
        Product product = service.getById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        ProductDTO dto = new ProductDTO(product.getId(), product.getName(), product.getPrice());
        return ResponseEntity.ok(dto);
    }


    @GetMapping("/discounts-file")
    public ResponseEntity<List<ProductDTO>> getFileDiscounts() {
        List<Product> products = service.getProductsDiscountFromFile();
        List<ProductDTO> dtos = products.stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getPrice()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    //implantar tratamento para desconto muito alto
    @GetMapping("/discounted")
    public ResponseEntity<List<ProductDTO>> getWithGlobalDiscount(@RequestParam double percent) {
        List<Product> products = service.getProductsDiscountUrl(percent);
        List<ProductDTO> dtos = products.stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getPrice()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
