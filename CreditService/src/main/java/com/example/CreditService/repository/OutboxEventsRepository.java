package com.example.CreditService.repository;

import com.example.CreditService.entity.OutboxEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxEventsRepository extends JpaRepository<OutboxEvents,Long> {
}
