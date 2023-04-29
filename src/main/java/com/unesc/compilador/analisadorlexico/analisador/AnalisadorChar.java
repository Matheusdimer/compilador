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
        char char2 = controller.getNext();
        char char3 = controller.getNext();

        String token = "" + char1 + char2 + char3;

        if (char3 != '\'') {
            throw new RegraLexaException("Esperado ' para fechamento do caracter, porém foi dado " + char3, token, controller.getRow());
        }

        return new Token(token, 39, controller.getRow());
    }
}
