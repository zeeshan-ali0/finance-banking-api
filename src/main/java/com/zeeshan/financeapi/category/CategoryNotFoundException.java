package com.zeeshan.financeapi.category;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id) {
        super("Category " + id + " not found");
    }
}