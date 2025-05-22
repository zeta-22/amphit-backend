package com.amphit.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 2000)
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    private String imageUrl;
    
    private String category;
    
    private String brand;
    
    private String model;
    
    private Integer year;
    
    private Boolean isFeatured;
    
    private Boolean isOutOfStock;
}
