package com.zeeshan.financeapi.transaction;

import com.zeeshan.financeapi.account.Account;
import com.zeeshan.financeapi.category.Category;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TransactionType type;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(length = 255)
    private String description;

    @Column(name = "occurred_on", nullable = false)
    private LocalDate occurredOn;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    void onCreate() {
        createdAt = Instant.now().truncatedTo(ChronoUnit.MICROS);
    }

    protected Transaction() {
        // required by JPA
    }

    public Transaction(Account account, Category category, TransactionType type,
                       BigDecimal amount, String description, LocalDate occurredOn) {
        this.account = account;
        this.category = category;
        this.type = type;
        this.amount = amount.setScale(2);
        this.description = description;
        this.occurredOn = occurredOn;
    }

    public void update(Category category, String description, LocalDate occurredOn) {
        this.category = category;
        this.description = description;
        this.occurredOn = occurredOn;
    }

    public Long getId() { return id; }
    public Account getAccount() { return account; }
    public Category getCategory() { return category; }
    public TransactionType getType() { return type; }
    public BigDecimal getAmount() { return amount; }
    public String getDescription() { return description; }
    public LocalDate getOccurredOn() { return occurredOn; }
    public Instant getCreatedAt() { return createdAt; }
}