package com.example.CreditService.component;

import com.example.CreditService.entity.CreditEntity;
import com.example.CreditService.repository.CreditRepository;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageConsumer {
    private final CreditRepository creditRepository;

    public RabbitMQMessageConsumer(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Transactional
    @RabbitListener(queues = "credit-decisions")
    public void receiveMessage(CreditEntity message) {
        creditRepository.updateStatus(message.getId(), message.getStatus());

    }
}
