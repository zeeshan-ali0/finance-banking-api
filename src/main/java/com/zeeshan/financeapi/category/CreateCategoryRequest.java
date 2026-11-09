package com.zeeshan.financeapi.category;

import com.zeeshan.financeapi.transaction.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequest(
        @NotBlank @Size(max = 50) String name,
        @NotNull TransactionType kind
) {}