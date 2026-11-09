package com.zeeshan.financeapi.transaction;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateTransactionRequest(
        @NotNull Long categoryId,
        @NotNull TransactionType type,
        @NotNull @Positive @Digits(integer = 17, fraction = 2) BigDecimal amount,
        @Size(max = 255) String description,
        @NotNull @PastOrPresent LocalDate occurredOn
) {}