package com.bank.itau.exception;

public class LimiteIndisponivelException extends RuntimeException {
    public LimiteIndisponivelException(String message) {
        super(message);
    }
}
