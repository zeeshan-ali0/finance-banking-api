package com.zeeshan.financeapi.category;

import com.zeeshan.financeapi.transaction.TransactionType;

public record CategoryResponse(Long id, String name, TransactionType kind) {

    static CategoryResponse from(Category category) {
        return new CategoryResponse(category.getId(), category.getName(), category.getKind());
    }
}