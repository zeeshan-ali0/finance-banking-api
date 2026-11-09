package com.zeeshan.financeapi.transaction;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record TransactionResponse(
        Long id,
        Long accountId,
        Long categoryId,
        String categoryName,
        TransactionType type,
        BigDecimal amount,
        String description,
        LocalDate occurredOn,
        Instant createdAt
) {
    static TransactionResponse from(Transaction t) {
        return new TransactionResponse(
                t.getId(),
                t.getAccount().getId(),
                t.getCategory().getId(),
                t.getCategory().getName(),
                t.getType(),
                t.getAmount(),
                t.getDescription(),
                t.getOccurredOn(),
                t.getCreatedAt());
    }
}