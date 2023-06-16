package com.unesc.compilador.analisadorsintatico;

import com.unesc.compilador.TokenParser;
import com.unesc.compilador.analisadorlexico.base.Token;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class AnalisadorSintatico {
    public boolean analisar(IGramatica gramatica, List<Token> tokens) {
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
                System.out.println("Token " + TokenParser.get(tokenEntrada) + " inesperado. Esperado final do código.");
                System.out.println("Linha do erro: " + (entrada.getLinha()));
                return false;
            }

            int numProximaProducao = gramatica.parse(producao, tokenEntrada);

            if (numProximaProducao == 0) {
                System.out.println("Token " + TokenParser.get(tokenEntrada) + " inesperado. Esperado token " + TokenParser.get(producao));
                System.out.println("Linha do erro: " + (entrada.getLinha()));
                return false;
            }

            final int[] proximaProducao = gramatica.getProducao(numProximaProducao);
            empilharReverso(producoes, proximaProducao);
        }

        if (producoes.isEmpty()) {
            System.out.println("Esperado token final porém as produções estão vazias");
            return false;
        }

        int ultimo = producoes.pop();

        if (ultimo != gramatica.get$()) {
            System.out.println("Esperado produção final $, porém foi dado a produção " + ultimo);
            return false;
        }

        return true;
    }

    private void empilharReverso(Stack<Integer> stack, int[] numeros) {
        for (int i = numeros.length - 1; i >= 0; i--) {
            if (numeros[i] != 0) {
                stack.push(numeros[i]);
            }
        }
    }
}
