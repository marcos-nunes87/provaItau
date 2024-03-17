package com.bank.itau.exception;

public class ContaNotFoundException extends RuntimeException {
	public ContaNotFoundException(String message) {
        super(message);
    }
}
