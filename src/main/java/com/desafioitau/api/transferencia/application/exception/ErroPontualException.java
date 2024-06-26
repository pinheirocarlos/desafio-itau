package com.desafioitau.api.transferencia.application.exception;

public class ErroPontualException extends RuntimeException {
    public ErroPontualException(String mensagem) {
        super(mensagem);
    }
}
