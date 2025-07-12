package com.example.meli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ProductDTO {
    private String id;
    private String name;
    private double price;
}
