package com.unesc.compilador.exceptions;

public class RegraSemanticaException extends RuntimeException {

    private final int linha;
    private final String token;

    public RegraSemanticaException(String message, int linha, String token) {
        super(message);
        this.linha = linha;
        this.token = token;
    }

    public int getLinha() {
        return linha;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "RegraSemanticaException{" +
                "linha=" + linha +
                ", token='" + token + '\'' +
                '}';
    }
}
