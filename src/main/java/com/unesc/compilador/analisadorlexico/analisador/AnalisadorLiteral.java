package com.unesc.compilador.analisadorlexico.analisador;

import com.unesc.compilador.analisadorlexico.base.AbstractAnalisadorExpressao;
import com.unesc.compilador.analisadorlexico.base.PointerController;
import com.unesc.compilador.analisadorlexico.base.Token;

import java.util.regex.Pattern;

public class AnalisadorLiteral extends AbstractAnalisadorExpressao {

    public AnalisadorLiteral() {
        super(4, Pattern.compile("\""));
    }

    @Override
    public Token analisar(PointerController controller) {
        return null;
    }
}
