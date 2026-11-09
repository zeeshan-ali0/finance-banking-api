package com.zeeshan.financeapi.category;

public class DuplicateCategoryException extends RuntimeException {
    public DuplicateCategoryException() {
        super("A category with this name already exists");
    }
}