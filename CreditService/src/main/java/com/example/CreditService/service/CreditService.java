package com.example.CreditService.service;

import com.example.CreditService.entity.CreditEntity;
import com.example.CreditService.entity.OutboxEvents;
import com.example.CreditService.enums.CreditOrderStatus;
import com.example.CreditService.model.CreditRequest;
import com.example.CreditService.repository.CreditRepository;
import com.example.CreditService.repository.OutboxEventsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Scope("request")
@Transactional
@Service
public class CreditService {
    private CreditRepository creditRepository;
    private OutboxEventsRepository outboxEventsRepository;
    private final ObjectMapper mapper;

    public CreditService(CreditRepository creditRepository,OutboxEventsRepository outboxEventsRepository,ObjectMapper mapper) {
        this.creditRepository = creditRepository;
        this.outboxEventsRepository = outboxEventsRepository;
        this.mapper = mapper;
    }
    public long submit(CreditRequest request) {
        CreditEntity app = new CreditEntity();
        app.setAmount(request.getAmount());
        app.setTerm(request.getTerm());
        app.setUserIncome(request.getUserIncome());
        app.setCurrentCreditLoad(request.getCurrentCreditLoad());
        app.setCreditRating(request.getCreditRating());
        app.setStatus(CreditOrderStatus.PROCESSING);

        app = creditRepository.save(app);

        String payload;
        try {
            payload = mapper.writeValueAsString(app);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize event", e);
        }
        var outbox = new OutboxEvents();
        outbox.setCreatedAt(ZonedDateTime.now());
        outbox.setPayload(payload);
        outbox.setPublished(false);
        outbox.setEventType("CreditApplicationSubmitted");

        outboxEventsRepository.save(outbox);

        return app.getId();


    }



    public Optional <CreditOrderStatus> getStatus(long id) {
        var result = creditRepository.findById(id);
        if(result.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(result.get().getStatus());
    }

}
