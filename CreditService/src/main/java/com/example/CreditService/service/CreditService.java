package com.example.CreditService.service;

import com.example.CreditService.enums.CreditOrderStatus;
import com.example.CreditService.model.CreditRequest;
import com.example.CreditService.repository.CreditRepository;
import com.example.CreditService.repository.OutboxEventsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditService {
    private CreditRepository creditRepository;
    private OutboxEventsRepository outboxEventsRepository;

    public CreditService(CreditRepository creditRepository,OutboxEventsRepository outboxEventsRepository) {
        this.creditRepository = creditRepository;
        this.outboxEventsRepository = outboxEventsRepository;
    }



    public long submit(CreditRequest request) {
        return -1;
    }

    public Optional <CreditOrderStatus> getStatus(long id) {
        var result = creditRepository.findById(id);
        if(result.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(result.get().getStatus());
    }

}
