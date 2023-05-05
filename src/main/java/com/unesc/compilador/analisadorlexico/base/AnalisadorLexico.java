package com.unesc.compilador.analisadorlexico.base;

import com.unesc.compilador.analisadorlexico.analisador.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AnalisadorLexico {
    
    private final Set<AnalisadorExpressao> analisadores = new TreeSet<>();
    private final PointerController controller = new PointerController();
    private final List<Token> tokens = new LinkedList<>();

    public AnalisadorLexico() {
        analisadores.add(new AnalisadorIdentificadores());
        analisadores.add(new AnalisadorNumeros());
        analisadores.add(new AnalisadorSimbolos());
        analisadores.add(new AnalisadorComentario());
        analisadores.add(new AnalisadorChar());
        analisadores.add(new AnalisadorLiteral());
    }
    
    public List<Token> analisar(String codigo) {
        tokens.clear();
        controller.setString(codigo);

        while (controller.hasNext()) {
            char next = controller.getNext();
            
            if (Character.isWhitespace(next)) {
                continue;
            }

            for (AnalisadorExpressao analisador : analisadores) {
                if (analisador.matchRegex(next)) {
                    controller.back();
                    controller.saveCheckpoint();
                    Token token = analisador.analisar(controller);

                    if (token != null) {
                        tokens.add(token);
                    }
                    break;
                }
            }
        }
        
        return tokens;
    }
}
