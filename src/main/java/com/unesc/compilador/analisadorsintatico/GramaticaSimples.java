package com.unesc.compilador.analisadorsintatico;




public class GramaticaSimples implements IGramatica {

    private int[][] tabParsing = new int[9][6];

    //inicializar as produções
    private int[][] producoes = {
            {2,7,4},//P1
            {2,8,0},//P2
            {8,0,0},//P3
            {3,2,8},//P4
            {1,0,0},//P5
    };;

    // 2745 -> cAa$ | cbca -> 2324
    // 745 -> Aa$ | bca -> 324
    // 845 -> Ba$ | bca -> 324
    // 32845 -> bcBa$ | bca -> 324
    // 2845 -> cBa$ | ca -> 24
    // 845 -> Ba$ | a -> 4
    // 145 -> îa$ | a -> 4
    // 45 -> a$ | a -> 4
    // 5 -> $

    public GramaticaSimples() {
        //inicializar a Matriz de Parsing com zeros.
        for(int i=0; i<9; i++){
            for(int j=0; j<6; j++){
                tabParsing[i][j] = 0;
            }
        }

        //inicializar os outros elementos da Matriz de Parsing.
        tabParsing[6][2] = 1;
        tabParsing[7][2] = 2;
        tabParsing[7][3] = 3;
        tabParsing[8][3] = 4;
        tabParsing[8][4] = 5;
    }

    public int[] getProducao(int numero) {
        return producoes[numero - 1];
    }

    public int parse(int producao, int token) {
        return tabParsing[producao][token];
    }

    public int get$() {
        return 5;
    }

    public int getVazio() {
        return 1;
    }
}