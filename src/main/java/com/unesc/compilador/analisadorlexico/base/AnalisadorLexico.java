package com.unesc.compilador.analisadorlexico.base;

import com.unesc.compilador.analisadorlexico.analisador.AnalisadorComentario;
import com.unesc.compilador.analisadorlexico.analisador.AnalisadorIndentificadores;
import com.unesc.compilador.analisadorlexico.analisador.AnalisadorSimbolos;
import com.unesc.compilador.analisadorlexico.analisador.AnalisadorNumeros;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AnalisadorLexico {
    
    private final Set<AbstractAnalisadorExpressao> analisadores = new TreeSet<>();
    private final PointerController controller = new PointerController();
    private final List<Token> tokens = new LinkedList<>();

    public AnalisadorLexico() {
        analisadores.add(new AnalisadorIndentificadores());
        analisadores.add(new AnalisadorNumeros());
        analisadores.add(new AnalisadorSimbolos());
        analisadores.add(new AnalisadorComentario());
    }
    
    public List<Token> analisar(String codigo) {
        tokens.clear();
        controller.setString(codigo);

        while (controller.hasNext()) {
            char next = controller.getNext();
            
            if (Character.isWhitespace(next)) {
                continue;
            }

            for (AbstractAnalisadorExpressao analisador : analisadores) {
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
