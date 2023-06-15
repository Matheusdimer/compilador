package com.unesc.compilador.backups;

import com.unesc.compilador.analisadorsintatico.IGramatica;

public class Gramatica implements IGramatica {
	private final int[][] tabParsing = new int[83][54];

	//inicializar as produções
	int producoes[][] = {
			{10 ,17 ,43 ,55 ,47, 0, 0},
			{56, 57, 58, 59, 0, 0, 0, 0},
			{11 ,17 ,66 ,58 ,59, 43, 56, 0},
			{18, 0, 0, 0, 0, 0, 0, 0},
			{24 ,17 ,32 ,60 ,43 ,61, 0, 0},
			{18, 0, 0, 0, 0, 0, 0, 0},
			{23 ,62 ,44 ,60 ,43 ,63, 0, 0},
			{18, 0, 0, 0, 0, 0, 0, 0},
			{27 ,67 ,43 ,68 ,20, 0, 0, 0},
			{15, 0, 0, 0, 0, 0, 0, 0},
			{25, 0, 0, 0, 0, 0, 0, 0},
			{6, 0, 0, 0, 0, 0, 0, 0},
			{8, 0, 0, 0, 0, 0, 0, 0},
			{28 ,42 ,38 ,46 ,38 ,41 ,13 ,65},
			{17,32 ,60 ,43 ,61, 0, 0, 0},
			{18, 0, 0, 0, 0, 0, 0, 0},
			{17,64, 0, 0, 0, 0, 0, 0},
			{62,44,60,43,63, 0, 0, 0},
			{18, 0, 0, 0, 0, 0, 0, 0},
	};;

	public Gramatica(){

		//inicializar a Matriz de Parsing com zeros.
		for(int i=0; i<82; i++){
			for(int j=0; j<54; j++){
				tabParsing[i][j] = 0;
			}
		}


		//inicializar os outros elementos da Matriz de Parsing.
		tabParsing[54][10] = 1;
		tabParsing[55][11] = 2;
		tabParsing[55][23] = 2;
		tabParsing[55][24] = 2;
		tabParsing[55][27] = 2;
		tabParsing[56][27] = 4;
		tabParsing[57][27] = 6;
		tabParsing[58][27] = 8;
		tabParsing[56][11] = 3;
		tabParsing[56][24] = 4;
		tabParsing[56][47] = 4;
		tabParsing[57][23] = 6;
		tabParsing[57][24] = 5;
		tabParsing[57][47] = 6;
		tabParsing[58][22] = 7;
		tabParsing[58][47] = 8;
		tabParsing[59][27] = 9;
		tabParsing[60][6] = 11;
		tabParsing[60][8] = 10;
		tabParsing[60][15] = 12;
		tabParsing[60][25] = 13;
		tabParsing[60][28] = 14;
		tabParsing[61][17] = 15;
		tabParsing[61][23] = 16;
		tabParsing[62][17] = 17;
		tabParsing[63][17] = 18;
		tabParsing[63][27] = 19;
	}

	public int[] getProducao(int numero) {
		return producoes[numero - 1];
	}

	public int parse(int producao, int token) {
		return tabParsing[producao][token];
	}

	public int get$() {
		return 52;
	}

	public int getVazio() {
		return 18;
	}
}
