package com.unesc.compilador.analisadorlexico.analisador;

import com.unesc.compilador.analisadorlexico.base.AnalisadorExpressao;
import com.unesc.compilador.analisadorlexico.base.PointerController;
import com.unesc.compilador.analisadorlexico.base.RegraLexaException;
import com.unesc.compilador.analisadorlexico.base.Token;

import java.util.regex.Pattern;

public class AnalisadorNumeros extends AnalisadorExpressao {

    private static final int IDENTIFICADOR_INT_CODE = 37;
    private static final int IDENTIFICADOR_REAL_CODE = 36;

    public AnalisadorNumeros() {
        super(3, Pattern.compile("[0-9]"));
    }

    @Override
    public Token analisar(PointerController controller) {
        StringBuilder buffer = new StringBuilder();
        boolean isReal = false;

        while (controller.hasNext()) {
            char next = controller.getNext();

            if (!matchRegex(next) && next != '.' && next != '-') {
                break;
            }
            if (next == '.') {
                if (controller.hasNext()) {
                    char next2 = controller.getNext();
                    controller.back();
                    if (next2 == '.') {
                        break;
                    } else {
                        isReal = true;
                    }
                }
            }

            buffer.append(next);
        }

        if (controller.hasNext()) {
            controller.back();
        }
        this.validate(buffer.toString(), isReal, controller);
        return new Token(
                buffer.toString(),
                isReal ? IDENTIFICADOR_REAL_CODE : IDENTIFICADOR_INT_CODE,
                controller.getRow()
        );
    }

    private void validate(String numero, Boolean isReal, PointerController controller) {
        try {
            if (isReal) {
                double v = Double.parseDouble(numero);
                if (v > 10000) {
                    throw new RegraLexaException("Números reais não podem ser maiores que 10000.", numero, controller.getRow());
                }
                if (v < 0) {
                    throw new RegraLexaException("Números reais não podem ser menores que 0.", numero, controller.getRow());
                }
                if (numero.split("\\.")[1].length() > 2) {
                    throw new RegraLexaException("Números reais não podem ter mais que 2 casas decimais.", numero, controller.getRow());
                }
            } else {
                int num = Integer.parseInt(numero);
                if (num > 10000) {
                    throw new RegraLexaException("Números inteiros não podem ser maiores que 10000.", numero, controller.getRow());
                }
                if (num < 0) {
                    throw new RegraLexaException("Números inteiros não podem ser menores que 0.", numero, controller.getRow());
                }
            }
        } catch (NumberFormatException e) {
            throw new RegraLexaException("Número mal formatado.", numero, controller.getRow());
        }
    }
}
