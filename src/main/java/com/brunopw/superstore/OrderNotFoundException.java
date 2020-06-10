package com.brunopw.superstore;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String id) {
        super("Could not find order " + id);
    }
}
