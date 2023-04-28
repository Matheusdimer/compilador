package com.unesc.compilador.analisadorlexico.analisador;

import com.unesc.compilador.analisadorlexico.base.AbstractAnalisadorExpressao;
import com.unesc.compilador.analisadorlexico.base.PointerController;
import com.unesc.compilador.analisadorlexico.base.Token;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class AnalisadorIndentificadores extends AbstractAnalisadorExpressao {

    private static final int IDENTIFICADOR_CODE = 16;

    private final Map<String, Integer> palavrasReservadas = new HashMap<>();

    public AnalisadorIndentificadores() {
        super(1, Pattern.compile("[a-zA-Z]"));
        palavrasReservadas.put("write", 0);
        palavrasReservadas.put("while", 1);
        palavrasReservadas.put("until", 2);
        palavrasReservadas.put("to", 3);
        palavrasReservadas.put("then", 4);
        palavrasReservadas.put("repeat", 6);
        palavrasReservadas.put("real", 7);
        palavrasReservadas.put("read", 8);
        palavrasReservadas.put("program", 9);
        palavrasReservadas.put("procedure", 10);
        palavrasReservadas.put("or", 11);
        palavrasReservadas.put("of", 12);
        palavrasReservadas.put("if", 15);
        palavrasReservadas.put("for", 18);
        palavrasReservadas.put("end", 19);
        palavrasReservadas.put("else", 20);
        palavrasReservadas.put("do", 21);
        palavrasReservadas.put("declaravariavies", 22);
        palavrasReservadas.put("const", 23);
        palavrasReservadas.put("char", 24);
        palavrasReservadas.put("chamaprocedure", 25);
        palavrasReservadas.put("begin", 26);
        palavrasReservadas.put("array", 27);
        palavrasReservadas.put("and", 28);
    }

    @Override
    public Token analisar(PointerController controller) {
        StringBuilder buffer = new StringBuilder();

        while (controller.hasNext()) {
            char next = controller.getNext();

            if (!matchRegex(next)) {
                break;
            }

            buffer.append(next);
        }

        if (controller.hasNext()) {
            controller.back();
        }

        return new Token(
                buffer.toString(),
                palavrasReservadas.getOrDefault(buffer.toString(), IDENTIFICADOR_CODE),
                controller.getRow()
        );
    }
}
