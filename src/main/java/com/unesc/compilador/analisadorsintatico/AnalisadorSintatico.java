package com.unesc.compilador.analisadorsintatico;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class AnalisadorSintatico {
    public static void main(String[] args) {
        AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico();
        boolean valid = analisadorSintatico.analisar(
                new GramaticaSimples(),
                Arrays.asList(2, 3, 2, 4)
        );

        System.out.println("Gramática válida: " + valid);
    }

    public boolean analisar(IGramatica gramatica, List<Integer> tokens) {
        Stack<Integer> producoes = new Stack<>();
        Stack<Integer> entradas = new Stack<>();

        // Empilha os tokens de trás para frente
        Collections.reverse(tokens);
        tokens.forEach(entradas::push);

        // Empilha o $
        producoes.push(gramatica.get$());
        // Empilha a produção 1
        empilharReverso(producoes, gramatica.getProducao(1));

        while (!entradas.isEmpty()) {

            System.out.println("Produções: " + producoes);
            System.out.println("Entradas: " + entradas);
            System.out.println("-------------------------------------------------");

            int producao = producoes.pop();
            int entrada = entradas.pop();

            if (producao == entrada) {
                continue;
            }

            entradas.push(entrada);

            if (producao == gramatica.getVazio()) {
                continue;
            }

            if (producao == gramatica.get$()) {
                System.out.println("Token " + entrada + " inesperado. Esperado final do código.");
                return false;
            }

            int numProximaProducao = gramatica.parse(producao, entrada);

            if (numProximaProducao == 0) {
                System.out.println("Token " + entrada + " inesperado. Esperado token " + producao);
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
