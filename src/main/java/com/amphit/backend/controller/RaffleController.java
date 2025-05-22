package com.amphit.backend.controller;

import com.amphit.backend.dto.RaffleDto;
import com.amphit.backend.model.Raffle;
import com.amphit.backend.model.RaffleTicket;
import com.amphit.backend.model.User;
import com.amphit.backend.service.RaffleService;
import com.amphit.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/raffles")
public class RaffleController {
    private final RaffleService raffleService;
    private final UserService userService;

    public RaffleController(RaffleService raffleService, UserService userService) {
        this.raffleService = raffleService;
        this.userService = userService;
    }

    @GetMapping("/public/all")
    public ResponseEntity<List<RaffleDto>> getAllRaffles() {
        List<Raffle> raffles = raffleService.getAllRaffles();
        List<RaffleDto> raffleDtos = raffles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(raffleDtos);
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<RaffleDto> getRaffleById(@PathVariable Long id) {
        Raffle raffle = raffleService.getRaffleById(id);
        return ResponseEntity.ok(convertToDto(raffle));
    }

    @GetMapping("/public/active")
    public ResponseEntity<List<RaffleDto>> getActiveRaffles() {
        List<Raffle> raffles = raffleService.getActiveRaffles();
        List<RaffleDto> raffleDtos = raffles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(raffleDtos);
    }

    @GetMapping("/public/product/{productId}")
    public ResponseEntity<List<RaffleDto>> getRafflesByProduct(@PathVariable Long productId) {
        List<Raffle> raffles = raffleService.getRafflesByProduct(productId);
        List<RaffleDto> raffleDtos = raffles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(raffleDtos);
    }

    // Authenticated endpoints
    
    @PostMapping
    public ResponseEntity<RaffleDto> createRaffle(@Valid @RequestBody RaffleDto raffleDto) {
        Raffle raffle = raffleService.createRaffle(
                raffleDto.getProductId(),
                raffleDto.getStartTime(),
                raffleDto.getEndTime(),
                raffleDto.getMaxTickets(),
                raffleDto.getTicketPrice()
        );
        return ResponseEntity.ok(convertToDto(raffle));
    }

    @PostMapping("/{raffleId}/purchase")
    public ResponseEntity<?> purchaseTicket(@PathVariable Long raffleId, @RequestParam(required = false, defaultValue = "false") Boolean isFree) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(auth.getName());
        
        RaffleTicket ticket = raffleService.purchaseTicket(raffleId, user.getId(), isFree);
        
        return ResponseEntity.ok("Ticket purchased successfully! Ticket Number: " + ticket.getTicketNumber());
    }

    @PostMapping("/{raffleId}/draw")
    public ResponseEntity<RaffleDto> drawWinner(@PathVariable Long raffleId) {
        Raffle raffle = raffleService.drawWinner(raffleId);
        return ResponseEntity.ok(convertToDto(raffle));
    }
    
    private RaffleDto convertToDto(Raffle raffle) {
        RaffleDto dto = new RaffleDto();
        dto.setId(raffle.getId());
        dto.setProductId(raffle.getProduct().getId());
        dto.setProductName(raffle.getProduct().getName());
        dto.setProductImageUrl(raffle.getProduct().getImageUrl());
        dto.setStartTime(raffle.getStartTime());
        dto.setEndTime(raffle.getEndTime());
        dto.setMaxTickets(raffle.getMaxTickets());
        dto.setTicketsSold(raffle.getTicketsSold());
        dto.setTicketsRemaining(raffle.getTicketsRemaining());
        dto.setTicketPrice(raffle.getTicketPrice());
        dto.setIsActive(raffle.getIsActive());
        dto.setStatus(raffle.getStatus());
        dto.setDrawTime(raffle.getDrawTime());
        
        if (raffle.getWinner() != null) {
            dto.setWinnerId(raffle.getWinner().getId());
            dto.setWinnerUsername(raffle.getWinner().getUsername());
        }
        
        return dto;
    }
}
