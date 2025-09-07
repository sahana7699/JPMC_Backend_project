package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionConsumer {

    private final TransactionService txService;

    public TransactionConsumer(TransactionService txService) {
        this.txService = txService;
    }

    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas-core")
    public void handle(Transaction tx) {
        txService.validateAndApply(tx);
    }
}
