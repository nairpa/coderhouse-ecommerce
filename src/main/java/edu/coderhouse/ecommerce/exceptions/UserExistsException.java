package edu.coderhouse.ecommerce.exceptions;

public class UserExistsException extends RuntimeException {
    public UserExistsException(String msg) {
        super(msg);
    }
}
