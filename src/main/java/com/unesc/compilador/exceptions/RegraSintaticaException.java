package com.unesc.compilador.exceptions;

public class RegraSintaticaException extends RuntimeException {

    private final int linha;

    public RegraSintaticaException(String message, int linha) {
        super(message);
        this.linha = linha;
    }

    public int getLinha() {
        return linha;
    }

    @Override
    public String toString() {
        return "RegraSintaticaException{" +
                "linha=" + linha +
                '}';
    }
}
