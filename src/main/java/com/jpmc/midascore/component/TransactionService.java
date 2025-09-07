package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRepository;
import com.jpmc.midascore.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final RestTemplate restTemplate;

    public TransactionService(UserRepository userRepository,
                              TransactionRepository transactionRepository,
                              RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public void validateAndApply(Transaction tx) {
        // Your UserRepository returns entity (or null), per your earlier constraint
        UserRecord sender    = userRepository.findById(tx.getSenderId());
        UserRecord recipient = userRepository.findById(tx.getRecipientId());
        if (sender == null || recipient == null) return;

        float amount = tx.getAmount();
        if (amount <= 0f) return;
        if (sender.getBalance() < amount) return;

        // --- NEW: call Incentive API ---
        float incentive = 0f;
        try {
            Incentive resp = restTemplate.postForObject(
                    "http://localhost:8080/incentive",
                    tx,                        // Spring will JSON-serialize the Transaction DTO
                    Incentive.class);
            if (resp != null && resp.getAmount() >= 0f) {
                incentive = resp.getAmount();
            }
        } catch (RestClientException e) {
            // If the service is down or fails, treat incentive as 0 and continue
            incentive = 0f;
        }

        // Apply balances
        sender.setBalance(sender.getBalance() - amount);                 // sender pays ONLY the amount
        recipient.setBalance(recipient.getBalance() + amount + incentive); // recipient gets amount + incentive

        // Persist
        userRepository.save(sender);
        userRepository.save(recipient);
        transactionRepository.save(new TransactionRecord(sender, recipient, amount, incentive));
    }
}
