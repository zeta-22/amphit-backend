package com.amphit.backend.repository;

import com.amphit.backend.model.Raffle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RaffleRepository extends JpaRepository<Raffle, Long> {
    List<Raffle> findByIsActive(Boolean isActive);
    
    List<Raffle> findByStatus(Raffle.RaffleStatus status);
    
    List<Raffle> findByEndTimeBefore(LocalDateTime dateTime);
    
    List<Raffle> findByEndTimeAfter(LocalDateTime dateTime);
    
    List<Raffle> findByProductId(Long productId);
}
