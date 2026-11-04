package com.zeeshan.financeapi.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateAccountRequest(
        @NotBlank @Size(max = 100) String name,
        @NotNull AccountType type,
        @NotBlank @Pattern(regexp = "[A-Z]{3}", message = "must be a 3-letter code like EUR")
        String currency
) {}