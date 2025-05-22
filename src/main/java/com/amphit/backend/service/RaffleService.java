package com.amphit.backend.service;

import com.amphit.backend.model.Product;
import com.amphit.backend.model.Raffle;
import com.amphit.backend.model.RaffleTicket;
import com.amphit.backend.model.User;
import com.amphit.backend.repository.RaffleRepository;
import com.amphit.backend.repository.RaffleTicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class RaffleService {
    private final RaffleRepository raffleRepository;
    private final RaffleTicketRepository ticketRepository;
    private final ProductService productService;
    private final UserService userService;

    public RaffleService(RaffleRepository raffleRepository, 
                        RaffleTicketRepository ticketRepository, 
                        ProductService productService, 
                        UserService userService) {
        this.raffleRepository = raffleRepository;
        this.ticketRepository = ticketRepository;
        this.productService = productService;
        this.userService = userService;
    }

    public List<Raffle> getAllRaffles() {
        return raffleRepository.findAll();
    }

    public Raffle getRaffleById(Long id) {
        return raffleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raffle not found with id: " + id));
    }

    public List<Raffle> getActiveRaffles() {
        return raffleRepository.findByIsActive(true);
    }

    public List<Raffle> getRafflesByStatus(Raffle.RaffleStatus status) {
        return raffleRepository.findByStatus(status);
    }

    public List<Raffle> getRafflesByProduct(Long productId) {
        return raffleRepository.findByProductId(productId);
    }

    @Transactional
    public Raffle createRaffle(Long productId, LocalDateTime startTime, LocalDateTime endTime, 
                               Integer maxTickets, Double ticketPrice) {
        Product product = productService.getProductById(productId);
        
        Raffle raffle = new Raffle();
        raffle.setProduct(product);
        raffle.setStartTime(startTime);
        raffle.setEndTime(endTime);
        raffle.setMaxTickets(maxTickets);
        raffle.setTicketPrice(ticketPrice);
        
        // Set status based on start time
        if (startTime.isBefore(LocalDateTime.now())) {
            raffle.setStatus(Raffle.RaffleStatus.ACTIVE);
        } else {
            raffle.setStatus(Raffle.RaffleStatus.PENDING);
        }
        
        return raffleRepository.save(raffle);
    }

    @Transactional
    public RaffleTicket purchaseTicket(Long raffleId, Long userId, Boolean isFree) {
        Raffle raffle = getRaffleById(raffleId);
        User user = userService.getUserById(userId);
        
        // Check if raffle is active
        if (!raffle.getIsActive() || raffle.getStatus() != Raffle.RaffleStatus.ACTIVE) {
            throw new RuntimeException("Raffle is not active!");
        }
        
        // Check if tickets are available
        if (raffle.getTicketsSold() >= raffle.getMaxTickets()) {
            throw new RuntimeException("No tickets available for this raffle!");
        }
        
        // Create ticket
        RaffleTicket ticket = new RaffleTicket();
        ticket.setUser(user);
        ticket.setRaffle(raffle);
        ticket.setPurchaseDate(LocalDateTime.now());
        ticket.setIsFree(isFree);
        
        // Generate ticket number (simple implementation)
        ticket.setTicketNumber(generateTicketNumber(raffleId));
        
        // Add loyalty points to user if not a free ticket
        if (!isFree) {
            userService.addLoyaltyPoints(userId, 10);
        }
        
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Raffle drawWinner(Long raffleId) {
        Raffle raffle = getRaffleById(raffleId);
        
        // Check if raffle is ready for drawing
        if (raffle.getStatus() != Raffle.RaffleStatus.ACTIVE || LocalDateTime.now().isBefore(raffle.getEndTime())) {
            throw new RuntimeException("Raffle is not ready for drawing!");
        }
        
        List<RaffleTicket> tickets = ticketRepository.findByRaffleId(raffleId);
        
        if (tickets.isEmpty()) {
            raffle.setStatus(Raffle.RaffleStatus.CANCELLED);
            return raffleRepository.save(raffle);
        }
        
        // Randomly select a winner
        Random random = new Random();
        RaffleTicket winningTicket = tickets.get(random.nextInt(tickets.size()));
        
        // Update raffle
        raffle.setWinner(winningTicket.getUser());
        raffle.setStatus(Raffle.RaffleStatus.COMPLETED);
        raffle.setDrawTime(LocalDateTime.now());
        raffle.setIsActive(false);
        
        return raffleRepository.save(raffle);
    }

    private String generateTicketNumber(Long raffleId) {
        // Simple implementation to generate ticket number
        return raffleId + "-" + System.currentTimeMillis() + "-" + (new Random().nextInt(900) + 100);
    }
}
