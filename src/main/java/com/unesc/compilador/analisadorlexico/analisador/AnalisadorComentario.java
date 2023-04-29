package com.unesc.compilador.analisadorlexico.analisador;

import com.unesc.compilador.analisadorlexico.base.AnalisadorExpressao;
import com.unesc.compilador.analisadorlexico.base.PointerController;
import com.unesc.compilador.analisadorlexico.base.Token;

import java.util.regex.Pattern;

public class AnalisadorComentario extends AnalisadorExpressao {
    public AnalisadorComentario() {
        super(1, Pattern.compile("#"));
    }

    @Override
    public Token analisar(PointerController controller) {
        controller.getNext();
        char next = controller.getNext();

        if (next == '#') {
            while (controller.hasNext() && controller.getNext() != '#') {
                char nextOfNext = controller.getNext();

                if (nextOfNext == '#') {
                    break;
                }
            }
        } else {
            while (controller.hasNext() && controller.getNext() != '\n');
        }

        return null;
    }
}
