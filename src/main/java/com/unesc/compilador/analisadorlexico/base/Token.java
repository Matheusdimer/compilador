package com.unesc.compilador.analisadorlexico.base;

public class Token {
    private final String token;
    private final int codigo;
    private final int linha;

    public Token(String token, int codigo, int linha) {
        this.token = token;
        this.codigo = codigo;
        this.linha = linha;
    }

    public String getToken() {
        return token;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getLinha() {
        return linha;
    }

    @Override
    public String toString() {
        return Integer.toString(codigo);
    }
}
