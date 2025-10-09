package com.lucas.crudcarros.exception;

public class ValidationException extends RuntimeException {
	
	private final String campo;
    
    public ValidationException(String campo, String mensagem) {
        super(mensagem);
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }
}