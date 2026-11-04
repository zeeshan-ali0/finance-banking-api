package com.zeeshan.financeapi.account;

import java.math.BigDecimal;
import java.time.Instant;

public record AccountResponse(
        Long id,
        String name,
        AccountType type,
        String currency,
        BigDecimal balance,
        Instant createdAt
) {
    static AccountResponse from(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getName(),
                account.getType(),
                account.getCurrency(),
                account.getBalance(),
                account.getCreatedAt());
    }
}