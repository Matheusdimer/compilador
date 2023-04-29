package com.unesc.compilador.analisadorlexico.analisador;

import com.unesc.compilador.analisadorlexico.base.AnalisadorExpressao;
import com.unesc.compilador.analisadorlexico.base.PointerController;
import com.unesc.compilador.analisadorlexico.base.RegraLexaException;
import com.unesc.compilador.analisadorlexico.base.Token;

import java.util.regex.Pattern;

public class AnalisadorLiteral extends AnalisadorExpressao {

    public AnalisadorLiteral() {
        super(5, Pattern.compile("\""));
    }

    @Override
    public Token analisar(PointerController controller) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("\"");
        boolean literalIsFinished = false;

        if (controller.hasNext()) {
            controller.getNext();
        }

        while (controller.hasNext()) {
            char token = controller.getNext();

            if (matchRegex(token)) {
                literalIsFinished = true;
                break;
            }
            if (token == '\n') {
                throw new RegraLexaException("Não pode pular linha sem fechar o literal", buffer.toString(), controller.getRow());
            }

            buffer.append(token);
        }
        if (!literalIsFinished) {
            throw new RegraLexaException("Literal precisa ser finalizado", buffer.toString(), controller.getRow());
        }
        buffer.append("\"");
        if (controller.hasNext()) {
            controller.back();
        }

        return new Token(buffer.toString(), 13, controller.getRow());
    }
}
