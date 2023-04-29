package com.unesc.compilador.analisadorlexico.analisador;

import com.unesc.compilador.analisadorlexico.base.AbstractAnalisadorExpressao;
import com.unesc.compilador.analisadorlexico.base.PointerController;
import com.unesc.compilador.analisadorlexico.base.RegraLexaException;
import com.unesc.compilador.analisadorlexico.base.Token;

import java.util.regex.Pattern;

public class AnalisadorChar extends AbstractAnalisadorExpressao {

    public AnalisadorChar() {
        super(4, Pattern.compile("'"));
    }

    @Override
    public Token analisar(PointerController controller) {
        char char1 = controller.getNext();

        if (!controller.hasNext()) {
            throw new RegraLexaException("Término inesperado do token de caractere",
                    Character.toString(char1), controller.getRow());
        }

        char char2 = controller.getNext();

        if (!controller.hasNext()) {
            throw new RegraLexaException("Término inesperado do token de caractere",
                    Character.toString(char2), controller.getRow());
        }

        char char3 = controller.getNext();

        String token = "" + char1 + char2 + char3;

        if (char3 != '\'') {
            throw new RegraLexaException("Esperado ' para fechamento do caracter, " +
                    "porém foi dado o caractere '" + char3 + "'.", token, controller.getRow());
        }

        return new Token(token, 39, controller.getRow());
    }
}
