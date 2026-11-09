package com.zeeshan.financeapi.transaction;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record UpdateTransactionRequest(
        @NotNull Long categoryId,
        @Size(max = 255) String description,
        @NotNull @PastOrPresent LocalDate occurredOn
) {}