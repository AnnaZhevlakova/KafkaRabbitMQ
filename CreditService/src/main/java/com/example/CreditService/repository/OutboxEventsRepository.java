package com.example.CreditService.repository;

import com.example.CreditService.entity.OutboxEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Scope("request")
@Repository
public interface OutboxEventsRepository extends JpaRepository<OutboxEvent, Long> {

    @Query("SELECT u FROM OutboxEvent u WHERE u.published = false")
    List<OutboxEvent> findByPublishedFalse();
}
