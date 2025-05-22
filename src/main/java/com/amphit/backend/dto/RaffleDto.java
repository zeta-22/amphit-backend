package com.amphit.backend.dto;

import com.amphit.backend.model.Raffle;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RaffleDto {
    private Long id;
    
    @NotNull
    private Long productId;
    
    private String productName;
    
    private String productImageUrl;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    @NotNull
    @Positive
    private Integer maxTickets;
    
    private Integer ticketsSold;
    
    private Integer ticketsRemaining;

    private Double ticketPrice;

    private Boolean isActive;
    
    private Raffle.RaffleStatus status;
    
    private LocalDateTime drawTime;
    
    private Long winnerId;
    
    private String winnerUsername;
}
