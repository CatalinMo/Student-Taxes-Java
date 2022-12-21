package com.student.taxes.exception;

public class BadUserException extends RuntimeException {
    public BadUserException(String message) {
        super(message);
    }
}
