package com.unesc.compilador.analisadorlexico.base;

import java.util.regex.Pattern;

public abstract class AbstractAnalisadorExpressao implements Comparable<AbstractAnalisadorExpressao> {

    private final int prioridade;
    private final Pattern pattern;

    public AbstractAnalisadorExpressao(int prioridade, Pattern pattern) {
        this.prioridade = prioridade;
        this.pattern = pattern;
    }

    public boolean matchRegex(char character) {
        return pattern.matcher("" + character).matches();
    }

    public abstract Token analisar(PointerController controller);

    @Override
    public int compareTo(AbstractAnalisadorExpressao other) {
        if (prioridade == other.prioridade) {
            return 0;
        }

        return prioridade > other.prioridade ? 1 : -1;
    }
}
