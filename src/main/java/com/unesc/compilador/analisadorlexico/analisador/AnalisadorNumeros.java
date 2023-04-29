package com.unesc.compilador.analisadorlexico.analisador;

import com.unesc.compilador.analisadorlexico.base.AnalisadorExpressao;
import com.unesc.compilador.analisadorlexico.base.PointerController;
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

            if (!matchRegex(next) && next != '.') {
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

        return new Token(
                buffer.toString(),
                isReal ? IDENTIFICADOR_REAL_CODE : IDENTIFICADOR_INT_CODE,
                controller.getRow()
        );
    }
}
