package com.unesc.compilador.exceptions;

public class RegraLexaException extends RuntimeException {

    private final String token;
    private final int linha;

    public RegraLexaException(String message, String token, int linha) {
        super(message);
        this.token = token;
        this.linha = linha;
    }

    public String getToken() {
        return token;
    }

    public int getLinha() {
        return linha;
    }

    @Override
    public String toString() {
        return "RegraLexaException{" +
                "token='" + token + '\'' +
                ", linha=" + linha +
                '}';
    }
}
