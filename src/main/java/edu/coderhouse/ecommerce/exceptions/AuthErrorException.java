package edu.coderhouse.ecommerce.exceptions;

public class AuthErrorException extends RuntimeException {
    public AuthErrorException(String msg) {
        super(msg);
    }
}
