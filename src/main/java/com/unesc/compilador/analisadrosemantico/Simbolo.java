package com.unesc.compilador.analisadrosemantico;

public class Simbolo {

    private String nome;
    private Categoria categoria;
    private Tipo tipo;
    private String nivel;

    public Simbolo(String nome, Categoria categoria, Tipo tipo, String nivel) {
        this.nome = nome;
        this.categoria = categoria;
        this.tipo = tipo;
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getNivel() {
        return nivel;
    }

    enum Categoria {
        VARIAVEL, PROCEDURE, PARAMETRO
    }

    enum Tipo {
        REAL,INTEIRO,CHAR,STRING,ARRAY
    }
}
