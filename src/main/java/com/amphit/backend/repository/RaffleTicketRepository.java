package com.amphit.backend.repository;

import com.amphit.backend.model.RaffleTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaffleTicketRepository extends JpaRepository<RaffleTicket, Long> {
    List<RaffleTicket> findByUserId(Long userId);
    
    List<RaffleTicket> findByRaffleId(Long raffleId);
    
    Integer countByRaffleId(Long raffleId);
    
    List<RaffleTicket> findByUserIdAndRaffleId(Long userId, Long raffleId);
}
