package com.amphit.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "raffles")
public class Raffle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    @NotNull
    @Positive
    private Integer maxTickets;

    @OneToMany(mappedBy = "raffle", cascade = CascadeType.ALL)
    private Set<RaffleTicket> tickets = new HashSet<>();

    private Double ticketPrice;

    private Boolean isActive = true;
    
    @Enumerated(EnumType.STRING)
    private RaffleStatus status = RaffleStatus.PENDING;
    
    private LocalDateTime drawTime;
    
    @ManyToOne
    @JoinColumn(name = "winner_id")
    private User winner;
    
    public enum RaffleStatus {
        PENDING,
        ACTIVE,
        COMPLETED,
        CANCELLED
    }
    
    @Transient
    public Integer getTicketsSold() {
        return tickets.size();
    }
    
    @Transient
    public Integer getTicketsRemaining() {
        return maxTickets - tickets.size();
    }
}
