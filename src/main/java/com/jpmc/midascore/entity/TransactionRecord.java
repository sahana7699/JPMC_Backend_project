package com.jpmc.midascore.entity;

import jakarta.persistence.*;

@Entity
public class TransactionRecord {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserRecord sender;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserRecord recipient;

    @Column(nullable = false)
    private float amount;

    // NEW: store the incentive we got from the API
    @Column(nullable = false)
    private float incentive;

    protected TransactionRecord() { }

    public TransactionRecord(UserRecord sender, UserRecord recipient, float amount, float incentive) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.incentive = incentive;
    }

    public Long getId() { return id; }
    public UserRecord getSender() { return sender; }
    public UserRecord getRecipient() { return recipient; }
    public float getAmount() { return amount; }
    public float getIncentive() { return incentive; }
}
