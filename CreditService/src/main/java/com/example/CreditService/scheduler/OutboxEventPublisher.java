package com.example.CreditService.scheduler;


import com.example.CreditService.entity.OutboxEvent;
import com.example.CreditService.repository.OutboxEventsRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OutboxEventPublisher {
    private OutboxEventsRepository outboxR;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final Logger logger = LogManager.getLogger(OutboxEventPublisher.class);

    public OutboxEventPublisher(OutboxEventsRepository outboxR, KafkaTemplate<String, String> kafkaTemplate) {
        this.outboxR = outboxR;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void publishPendingEvents() {
        var events = outboxR.findByPublishedFalse();
        for (OutboxEvent event : events) {
            kafkaTemplate.send(event.getEventType(), event.getPayload());
            event.setPublished(true);
            outboxR.save(event);
        }
    }
}
