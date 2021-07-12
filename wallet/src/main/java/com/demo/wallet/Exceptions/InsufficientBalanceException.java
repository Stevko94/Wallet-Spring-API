package com.demo.wallet.Exceptions;

public class InsufficientBalanceException extends Exception {
    private String message;

    public InsufficientBalanceException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
