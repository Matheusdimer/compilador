package com.unesc.compilador.analisadrosemantico;

import com.unesc.compilador.analisadorlexico.base.Token;
import com.unesc.compilador.exceptions.RegraSemanticaException;

import java.util.ArrayList;
import java.util.List;

public class AnalisadorSemantico {

    private List<Simbolo> simbolos;
    boolean declarandoConst;
    boolean declarandoVar;

    public AnalisadorSemantico() {
        this.simbolos = new ArrayList<>();
        this.declarandoConst = false;
        this.declarandoVar = false;
    }

    public void analisar(List<Token> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if (token.isThisCodigo(16)) {
                if (tokens.get(i-1).isThisCodigo(9)) {
                } else if (declarandoConst){
                    if (exist(token.getToken())) {
                        throw new RegraSemanticaException("identificador já declarado", token.getLinha(), token.getToken());
                    } else {
                        simbolos.add(new Simbolo(token.getToken(), Simbolo.Categoria.VARIAVEL, this.getTypeConstByToken(tokens.get(i+2)), "DLC_CONST"));
                    }
                } else if (declarandoVar) {
                    if (exist(token.getToken())) {
                        throw new RegraSemanticaException("identificador já declarado", token.getLinha(), token.getToken());
                    } else {
                        simbolos.add(new Simbolo(token.getToken(), Simbolo.Categoria.VARIAVEL, this.getTypeVarByToken(tokens, i), "DLC_VAR"));
                    }
                } else if(!exist(token.getToken())) {
                    throw new RegraSemanticaException("identificador não declarado", token.getLinha(), token.getToken());
                }
            }
            if (token.isThisCodigo(23)){
                declarandoConst = true;
            }
            if (token.isThisCodigo(22)) {
                declarandoVar = true;
                declarandoConst = false;
            }
            if (token.isThisCodigo(26)) {
                declarandoVar = false;
                declarandoConst = false;
            }
        }
    }

    public boolean exist(String token) {
        return simbolos.stream().anyMatch(x -> x.getNome().equals(token));
    }

    public boolean exist(String token, String nivel) {
        return simbolos.stream().anyMatch(x -> x.getNome().equals(token) && x.getNivel().equals(nivel));
    }

    private Simbolo.Tipo getTypeVarByToken(List<Token> tokens, int i) {
        if (tokens.get(i+2).isThisCodigo(16)) {
            return getTypeVarByToken(tokens, i+2);
        }
        Token token = tokens.get(i + 2);
        if (token.isThisCodigo(24)) {
            return Simbolo.Tipo.CHAR;
        } else if (token.isThisCodigo(7)) {
            return Simbolo.Tipo.REAL;
        } else if (token.isThisCodigo(5)) {
            return Simbolo.Tipo.STRING;
        } else if (token.isThisCodigo(14)) {
            return Simbolo.Tipo.INTEIRO;
        }
        throw new RegraSemanticaException("Algum erro inesperado aconteceu", token.getCodigo(), token.getToken());
    }

    private Simbolo.Tipo getTypeConstByToken(Token token) {
        if (token.isThisCodigo(24)) {
            return Simbolo.Tipo.CHAR;
        } else if (token.isThisCodigo(7)) {
            return Simbolo.Tipo.REAL;
        } else if (token.isThisCodigo(5)) {
            return Simbolo.Tipo.STRING;
        } else if (token.isThisCodigo(14)) {
            return Simbolo.Tipo.INTEIRO;
        }
        throw new RegraSemanticaException("Algum erro inesperado aconteceu", token.getCodigo(), token.getToken());
    }
}
