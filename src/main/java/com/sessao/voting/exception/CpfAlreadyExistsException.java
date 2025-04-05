package com.sessao.voting.exception;

public class CpfAlreadyExistsException extends RuntimeException{
    public CpfAlreadyExistsException(String message) {
        super(message);
    }
}
