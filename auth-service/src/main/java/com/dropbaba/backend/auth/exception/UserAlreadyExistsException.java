package com.dropbaba.backend.auth.exception;

/**
 * Thrown when a user tries to register with an email/username that already exists.
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
