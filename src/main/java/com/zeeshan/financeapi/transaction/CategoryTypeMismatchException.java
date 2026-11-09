package com.zeeshan.financeapi.transaction;

public class CategoryTypeMismatchException extends RuntimeException {
    public CategoryTypeMismatchException() {
        super("Category kind does not match the transaction type");
    }
}