package com.unesc.compilador.analisadorsintatico;

import com.unesc.compilador.TokenParser;
import com.unesc.compilador.analisadorlexico.base.Token;
import com.unesc.compilador.analisadrosemantico.AnalisadorSemantico;
import com.unesc.compilador.exceptions.RegraSintaticaException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class AnalisadorSintatico {

    private AnalisadorSemantico analisadorSemantico;

    public AnalisadorSintatico() {
        this.analisadorSemantico = new AnalisadorSemantico();
    }

    public void analisar(IGramatica gramatica, List<Token> tokens) {
        Stack<Integer> producoes = new Stack<>();
        Stack<Token> entradas = new Stack<>();

        // Empilha os tokens de trás para frente
        Collections.reverse(tokens);
        for (Token token : tokens) {
            entradas.push(token);
        }

        // Empilha o $
        producoes.push(gramatica.get$());
        // Empilha a produção 1
        empilharReverso(producoes, gramatica.getProducao(1));

        while (!entradas.isEmpty()) {
            System.out.println("Produções: " + producoes);
            System.out.println("Entradas: " + entradas);
            System.out.println("-------------------------------------------------");

            int producao = producoes.pop();
            Token entrada = entradas.pop();
            int tokenEntrada = entrada.getCodigo() + 1;
            if (producao == tokenEntrada) {
                continue;
            }

            entradas.push(entrada);

            if (producao == gramatica.getVazio()) {
                continue;
            }

            if (producao == gramatica.get$()) {
                throw new RegraSintaticaException("Token " + TokenParser.get(tokenEntrada) + " inesperado. Esperado final do código.", entrada.getLinha());
            }

            int numProximaProducao = gramatica.parse(producao, tokenEntrada);

            if (numProximaProducao == 0) {
                throw new RegraSintaticaException("Token " + TokenParser.get(tokenEntrada) + " inesperado. Esperado token " + TokenParser.get(producao), entrada.getLinha());
            }

            final int[] proximaProducao = gramatica.getProducao(numProximaProducao);
            empilharReverso(producoes, proximaProducao);
        }

        if (producoes.isEmpty()) {
            throw new RegraSintaticaException("Esperado token final porém as produções estão vazias", 0);
        }

        int ultimo = producoes.pop();

        if (ultimo != gramatica.get$()) {
            throw new RegraSintaticaException("Esperado produção final $, porém foi dado a produção " + ultimo, 0);
        }
    }

    private void empilharReverso(Stack<Integer> stack, int[] numeros) {
        for (int i = numeros.length - 1; i >= 0; i--) {
            if (numeros[i] != 0) {
                stack.push(numeros[i]);
            }
        }
    }
}
