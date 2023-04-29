package com.unesc.compilador.analisadorlexico.analisador;

import com.unesc.compilador.analisadorlexico.base.AbstractAnalisadorExpressao;
import com.unesc.compilador.analisadorlexico.base.PointerController;
import com.unesc.compilador.analisadorlexico.base.Token;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class AnalisadorTokenSimples extends AbstractAnalisadorExpressao {
    private final Map<String, Integer> tokensSimples = new HashMap<>();

    public AnalisadorTokenSimples() {
        super(10, Pattern.compile("[^a-zA-Z0-9\\s]*$"));
        tokensSimples.put("=", 31);
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
    }

    @Override
    public Token analisar(PointerController controller) {
        String token = Character.toString(controller.getNext());
        Integer codigo = tokensSimples.get(token);

        if (codigo == null) {
            throw new RuntimeException("Token " + token + " não reconhecido");
        }

        return new Token(token, codigo, controller.getRow());
    }
}
