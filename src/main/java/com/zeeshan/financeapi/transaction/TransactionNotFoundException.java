package com.zeeshan.financeapi.transaction;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(Long id) {
        super("Transaction " + id + " not found");
    }
}