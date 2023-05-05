package com.unesc.compilador.analisadorlexico.base;

import java.util.regex.Pattern;

public abstract class AnalisadorExpressao implements Comparable<AnalisadorExpressao> {

    private final int prioridade;
    private final Pattern pattern;

    public AnalisadorExpressao(int prioridade, Pattern pattern) {
        this.prioridade = prioridade;
        this.pattern = pattern;
    }

    public boolean matchRegex(char character) {
        return pattern.matcher(String.valueOf(character)).matches();
    }

    public abstract Token analisar(PointerController controller);

    @Override
    public int compareTo(AnalisadorExpressao other) {
        if (prioridade == other.prioridade) {
            return 0;
        }

        return prioridade > other.prioridade ? 1 : -1;
    }
}
