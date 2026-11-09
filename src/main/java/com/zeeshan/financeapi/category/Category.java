package com.zeeshan.financeapi.category;

import com.zeeshan.financeapi.transaction.TransactionType;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TransactionType kind;

    protected Category() {
        // required by JPA
    }

    public Category(Long userId, String name, TransactionType kind) {
        this.userId = userId;
        this.name = name;
        this.kind = kind;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getName() { return name; }
    public TransactionType getKind() { return kind; }
}