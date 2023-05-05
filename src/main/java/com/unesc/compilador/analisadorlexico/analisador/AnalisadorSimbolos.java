package com.unesc.compilador.analisadorlexico.analisador;

import com.unesc.compilador.analisadorlexico.base.AnalisadorExpressao;
import com.unesc.compilador.analisadorlexico.base.PointerController;
import com.unesc.compilador.analisadorlexico.base.RegraLexaException;
import com.unesc.compilador.analisadorlexico.base.Token;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class AnalisadorSimbolos extends AnalisadorExpressao {
    private final Map<String, Integer> tokensSimples = new HashMap<>();

    public AnalisadorSimbolos() {
        super(10, Pattern.compile("[^a-zA-Z0-9\\s]*$"));
        tokensSimples.put("=", 31);
        tokensSimples.put(">=", 29);
        tokensSimples.put("<=", 33);
        tokensSimples.put("<>", 32);
        tokensSimples.put("..", 45);
        tokensSimples.put("+", 35);
        tokensSimples.put("]", 40);
        tokensSimples.put("[", 41);
        tokensSimples.put(";", 42);
        tokensSimples.put(":", 43);
        tokensSimples.put("/", 44);
        tokensSimples.put(",", 47);
        tokensSimples.put("*", 48);
        tokensSimples.put(")", 49);
        tokensSimples.put("(", 50);
        tokensSimples.put("-", 52);
        tokensSimples.put(".", 46);
    }

    @Override
    public Token analisar(PointerController controller) {
        StringBuilder buffer = new StringBuilder();

        while (controller.hasNext() && buffer.length() <= 2) {
            char token = controller.getNext();

            if (!matchRegex(token)) {
                break;
            }

            buffer.append(token);
        }

        if (controller.hasNext()) {
            controller.back();
        }

        Integer codigo = tokensSimples.get(buffer.toString());

        if (codigo == null && buffer.length() == 2) {
            buffer.deleteCharAt(1);
            controller.back();
            codigo = tokensSimples.get(buffer.toString());
        }

        String token = buffer.toString();

        if (codigo == null) {
            throw new RegraLexaException("Token " + token + " não reconhecido", token, controller.getRow());
        }

        return new Token(token, codigo, controller.getRow());
    }
}
