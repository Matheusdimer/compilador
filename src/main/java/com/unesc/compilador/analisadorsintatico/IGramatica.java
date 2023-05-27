package com.unesc.compilador.analisadorsintatico;

public interface IGramatica {
    int[] getProducao(int numero);

    int parse(int producao, int token);

    int get$();

    int getVazio();
}
