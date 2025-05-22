package com.amphit.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Raffle> raffles = new HashSet<>();
    
    private Boolean isFeatured = false;
    
    private Boolean isOutOfStock = false;
}
