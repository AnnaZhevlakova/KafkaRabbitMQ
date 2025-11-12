package com.example.CreditService.repository;

import com.example.CreditService.entity.CreditEntity;
import com.example.CreditService.enums.CreditOrderStatus;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Scope("request")
@Repository
public interface CreditRepository extends JpaRepository<CreditEntity, Long> {
    @Modifying
    @Query("UPDATE CreditEntity a SET a.status = :status WHERE a.id = :id")
    int updateStatus(@Param("id") Long id, @Param("status") CreditOrderStatus status);

}
