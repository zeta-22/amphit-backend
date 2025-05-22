package com.amphit.backend.repository;

import com.amphit.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
    
    List<Product> findByBrand(String brand);
    
    List<Product> findByIsFeatured(Boolean isFeatured);
    
    List<Product> findByIsOutOfStock(Boolean isOutOfStock);
    
    List<Product> findByNameContainingIgnoreCase(String keyword);
}
