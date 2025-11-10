package com.example.CreditService.repository;

import com.example.CreditService.entity.CreditEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Scope("request")
@Repository
public interface CreditRepository extends JpaRepository<CreditEntity, Long> {

}
