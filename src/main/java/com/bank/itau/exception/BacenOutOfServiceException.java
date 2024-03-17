package com.bank.itau.exception;

public class BacenOutOfServiceException extends RuntimeException {
    public BacenOutOfServiceException(String message) {
        super(message);
    }
}
