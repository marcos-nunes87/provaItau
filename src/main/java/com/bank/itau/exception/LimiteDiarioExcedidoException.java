package com.bank.itau.exception;

public class LimiteDiarioExcedidoException extends RuntimeException {
    public LimiteDiarioExcedidoException(String message) {
        super(message);
    }
}
