package com.amphit.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test-data")
public class TestDataController {

    @GetMapping("/status")
    public ResponseEntity<Map<String, String>> status() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "API is working!");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/products")
    public ResponseEntity<List<Map<String, Object>>> testProducts() {
        List<Map<String, Object>> products = new ArrayList<>();
        
        Map<String, Object> product1 = new HashMap<>();
        product1.put("id", 1);
        product1.put("name", "Test Product 1");
        product1.put("description", "This is a test product");
        product1.put("price", 99.99);
        
        Map<String, Object> product2 = new HashMap<>();
        product2.put("id", 2);
        product2.put("name", "Test Product 2");
        product2.put("description", "This is another test product");
        product2.put("price", 149.99);
        
        products.add(product1);
        products.add(product2);
        
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/raffles")
    public ResponseEntity<List<Map<String, Object>>> testRaffles() {
        List<Map<String, Object>> raffles = new ArrayList<>();
        
        Map<String, Object> raffle1 = new HashMap<>();
        raffle1.put("id", 1);
        raffle1.put("productName", "Test Raffle 1");
        raffle1.put("ticketPrice", 10.0);
        raffle1.put("maxTickets", 100);
        raffle1.put("ticketsSold", 25);
        
        Map<String, Object> raffle2 = new HashMap<>();
        raffle2.put("id", 2);
        raffle2.put("productName", "Test Raffle 2");
        raffle2.put("ticketPrice", 5.0);
        raffle2.put("maxTickets", 200);
        raffle2.put("ticketsSold", 75);
        
        raffles.add(raffle1);
        raffles.add(raffle2);
        
        return ResponseEntity.ok(raffles);
    }
}
