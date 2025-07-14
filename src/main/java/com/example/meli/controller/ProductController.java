package com.example.meli.controller;

import com.example.meli.domain.ProductNotFoundException;
import com.example.meli.domain.model.Product;
import com.example.meli.dto.ProductDTO;
import com.example.meli.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Produtos", description = "Endpoints relacionados a produtos")
public class ProductController {

    private final ProductService service;

    @GetMapping
    @Operation(summary = "Listar todos os produtos")
    public ResponseEntity<List<ProductDTO>> getAll() {

        List<Product> product = service.getAllProducts();

        List<ProductDTO> productDto = product.stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getPrice()))
                .collect(Collectors.toList());

        log.info("{} Produtos Retornados", productDto.size());
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID")
    public ResponseEntity<ProductDTO> getById(@PathVariable String id) {
        Product product = service.getById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        ProductDTO dto = new ProductDTO(product.getId(), product.getName(), product.getPrice());
        return ResponseEntity.ok(dto);
    }


    @GetMapping("/discounts-file")
    @Operation(summary = "Listar produtos com desconto baseado no JSON")
    public ResponseEntity<List<ProductDTO>> getFileDiscounts() {
        List<Product> product = service.getProductsDiscountFromFile();
        List<ProductDTO> productDto = product.stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getPrice()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDto);
    }

    //implantar tratamento para desconto muito alto
    @GetMapping("/discounted")
    @Operation(summary = "Listar produtos com desconto percentual")
    public ResponseEntity<List<ProductDTO>> getWithGlobalDiscount(@RequestParam double percent) {
        List<Product> product = service.getProductsDiscountUrl(percent);
        List<ProductDTO> productDto = product.stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getPrice()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDto);
    }
}
