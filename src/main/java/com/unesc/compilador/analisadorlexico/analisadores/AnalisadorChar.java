package com.unesc.compilador.analisadorlexico.analisadores;

import com.unesc.compilador.analisadorlexico.base.AnalisadorExpressao;
import com.unesc.compilador.analisadorlexico.base.PointerController;
import com.unesc.compilador.analisadorlexico.base.RegraLexaException;
import com.unesc.compilador.analisadorlexico.base.Token;

import java.util.regex.Pattern;

public class AnalisadorChar extends AnalisadorExpressao {

    public AnalisadorChar() {
        super(4, Pattern.compile("'"));
    }

    @Override
    public Token analisar(PointerController controller) {
        char char1 = controller.getNext();
        checarTerminoInesperado(controller, char1);

        char char2 = controller.getNext();
        checarTerminoInesperado(controller, char2);

        char char3 = controller.getNext();

        String token = "" + char1 + char2 + char3;

        if (char3 != '\'') {
            throw new RegraLexaException("Esperado ' para fechamento do caracter, " +
                    "porém foi dado o caractere '" + char3 + "'.", token, controller.getRow());
        }

        return new Token(token, 17, controller.getRow());
    }

    private void checarTerminoInesperado(PointerController controller, char character) {
        if (character == '\n') {
            throw new RegraLexaException("Quebra de linha inesperada dentro do token de caracter",
                    Character.toString(character), controller.getRow());
        }

        if (!controller.hasNext()) {
            throw new RegraLexaException("Término inesperado do token de caractere",
                    Character.toString(character), controller.getRow());
        }
    }
}
