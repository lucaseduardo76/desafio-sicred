package com.sessao.voting.exception;



public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}