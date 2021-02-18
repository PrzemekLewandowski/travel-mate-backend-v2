package com.travelmate.service.exception;

public class SignUpRequestValidationException extends RuntimeException {
    public SignUpRequestValidationException(String message) {
        super(message);
    }
}
