package com.unesc.compilador.analisadorlexico.analisadores;

import com.unesc.compilador.analisadorlexico.base.AnalisadorExpressao;
import com.unesc.compilador.analisadorlexico.base.PointerController;
import com.unesc.compilador.analisadorlexico.base.RegraLexaException;
import com.unesc.compilador.analisadorlexico.base.Token;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class AnalisadorIdentificadores extends AnalisadorExpressao {

    private static final int IDENTIFICADOR_CODE = 21;

    private final Map<String, Integer> palavrasReservadas = new HashMap<>();

    public AnalisadorIdentificadores() {
        super(2, Pattern.compile("[a-zA-Z]"));
        palavrasReservadas.put("write", 1);
        palavrasReservadas.put("while", 2);
        palavrasReservadas.put("until", 3);
        palavrasReservadas.put("to", 4);
        palavrasReservadas.put("then", 5);
        palavrasReservadas.put("repeat", 7);
        palavrasReservadas.put("real", 8);
        palavrasReservadas.put("read", 9);
        palavrasReservadas.put("program", 10);
        palavrasReservadas.put("procedure", 11);
        palavrasReservadas.put("or", 12);
        palavrasReservadas.put("of", 13);
        palavrasReservadas.put("if", 20);
        palavrasReservadas.put("for", 23);
        palavrasReservadas.put("end", 24);
        palavrasReservadas.put("else", 25);
        palavrasReservadas.put("do", 26);
        palavrasReservadas.put("declaravariaveis", 27);
        palavrasReservadas.put("const", 28);
        palavrasReservadas.put("char", 29);
        palavrasReservadas.put("chamaprocedure", 30);
        palavrasReservadas.put("begin", 31);
        palavrasReservadas.put("array", 32);
        palavrasReservadas.put("and", 33);
        palavrasReservadas.put("integer", 19);
        palavrasReservadas.put("string", 6);
    }

    @Override
    public Token analisar(PointerController controller) {
        StringBuilder buffer = new StringBuilder();

        while (controller.hasNext()) {
            char next = controller.getNext();

            if (!matchRegex(next) && !Character.isDigit(next)) {
                controller.back();
                break;
            }

            buffer.append(next);
        }

        Integer codigoToken = palavrasReservadas.getOrDefault(buffer.toString(), IDENTIFICADOR_CODE);

        if (codigoToken == IDENTIFICADOR_CODE) {
            this.validate(buffer.toString(), controller);
        }

        return new Token(
                buffer.toString(),
                codigoToken,
                controller.getRow()
        );
    }

    private void validate(String identificador, PointerController controller) {
        if (identificador.length() > 20) {
            throw new RegraLexaException("Identificadores não podem ter mais que 20 caracteres.", identificador, controller.getRow());
        }
    }
}
