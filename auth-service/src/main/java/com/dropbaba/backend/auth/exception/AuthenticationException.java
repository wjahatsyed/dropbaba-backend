package com.dropbaba.backend.auth.exception;

/**
 * Thrown when login credentials are invalid.
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }
}
