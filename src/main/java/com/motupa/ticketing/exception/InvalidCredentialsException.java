package com.motupa.ticketing.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(){
        super("Invalid email or password");
    }
}
