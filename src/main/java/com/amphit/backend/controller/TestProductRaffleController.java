package com.amphit.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This controller provides test endpoints that mimic the original endpoints
 * but without any database dependencies or security requirements.
 */
@RestController
@RequestMapping("/test-api")
public class TestProductRaffleController {

    @GetMapping("/products/public/all")
    public ResponseEntity<List<Map<String, Object>>> getTestProducts() {
        List<Map<String, Object>> products = new ArrayList<>();
        
        Map<String, Object> product1 = new HashMap<>();
        product1.put("id", 1);
        product1.put("name", "Test Product 1");
        product1.put("description", "This is a test product");
        product1.put("price", 99.99);
        product1.put("imageUrl", "https://via.placeholder.com/150");
        product1.put("brand", "Test Brand");
        product1.put("category", "Test Category");
        
        Map<String, Object> product2 = new HashMap<>();
        product2.put("id", 2);
        product2.put("name", "Test Product 2");
        product2.put("description", "This is another test product");
        product2.put("price", 149.99);
        product2.put("imageUrl", "https://via.placeholder.com/150");
        product2.put("brand", "Premium Brand");
        product2.put("category", "Premium Category");
        
        products.add(product1);
        products.add(product2);
        
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/raffles/public/all")
    public ResponseEntity<List<Map<String, Object>>> getTestRaffles() {
        List<Map<String, Object>> raffles = new ArrayList<>();
        
        // Get current time and calculate future dates
        long now = System.currentTimeMillis();
        long oneDay = 24 * 60 * 60 * 1000; // 1 day in milliseconds
        
        // Active raffle ending in 3 days
        Map<String, Object> raffle1 = new HashMap<>();
        raffle1.put("id", 1);
        raffle1.put("productName", "Air Jordan Sneakers");
        raffle1.put("description", "Limited edition collector's Air Jordan sneakers");
        raffle1.put("productImageUrl", "https://via.placeholder.com/300x300?text=Sneakers");
        raffle1.put("ticketPrice", 10.0);
        raffle1.put("maxTickets", 100);
        raffle1.put("ticketsSold", 65);
        raffle1.put("status", "ACTIVE");
        raffle1.put("startTime", new java.util.Date(now - (2 * oneDay)).toInstant().toString());
        raffle1.put("endTime", new java.util.Date(now + (3 * oneDay)).toInstant().toString());
        
        // Another active raffle ending in 5 days
        Map<String, Object> raffle2 = new HashMap<>();
        raffle2.put("id", 2);
        raffle2.put("productName", "PlayStation 5 Console");
        raffle2.put("description", "Brand new PlayStation 5 gaming console");
        raffle2.put("productImageUrl", "https://via.placeholder.com/300x300?text=PS5");
        raffle2.put("ticketPrice", 5.0);
        raffle2.put("maxTickets", 200);
        raffle2.put("ticketsSold", 120);
        raffle2.put("status", "ACTIVE");
        raffle2.put("startTime", new java.util.Date(now - (3 * oneDay)).toInstant().toString());
        raffle2.put("endTime", new java.util.Date(now + (5 * oneDay)).toInstant().toString());
        
        // Upcoming raffle starting in 2 days
        Map<String, Object> raffle3 = new HashMap<>();
        raffle3.put("id", 3);
        raffle3.put("productName", "Apple MacBook Pro");
        raffle3.put("description", "The latest MacBook Pro with M2 chip");
        raffle3.put("productImageUrl", "https://via.placeholder.com/300x300?text=MacBook");
        raffle3.put("ticketPrice", 20.0);
        raffle3.put("maxTickets", 150);
        raffle3.put("ticketsSold", 0);
        raffle3.put("status", "PENDING");
        raffle3.put("startTime", new java.util.Date(now + (2 * oneDay)).toInstant().toString());
        raffle3.put("endTime", new java.util.Date(now + (10 * oneDay)).toInstant().toString());
        
        // Completed raffle
        Map<String, Object> raffle4 = new HashMap<>();
        raffle4.put("id", 4);
        raffle4.put("productName", "Nintendo Switch OLED");
        raffle4.put("description", "Nintendo Switch with enhanced OLED display");
        raffle4.put("productImageUrl", "https://via.placeholder.com/300x300?text=Switch");
        raffle4.put("ticketPrice", 8.0);
        raffle4.put("maxTickets", 100);
        raffle4.put("ticketsSold", 100);
        raffle4.put("status", "COMPLETED");
        raffle4.put("startTime", new java.util.Date(now - (10 * oneDay)).toInstant().toString());
        raffle4.put("endTime", new java.util.Date(now - (2 * oneDay)).toInstant().toString());
        raffle4.put("winnerTicketNumber", "056");
        
        // Add all raffles to the list
        raffles.add(raffle1);
        raffles.add(raffle2);
        raffles.add(raffle3);
        raffles.add(raffle4);
        
        return ResponseEntity.ok(raffles);
    }
}
