package com.unesc.compilador;

import java.util.HashMap;
import java.util.Map;

public class TokenParser {

    private static final Map<Integer,String> palavrasReservadas = new HashMap<>();

    static  {
        palavrasReservadas.put(39, "char");
        palavrasReservadas.put(17, "vazio");
        palavrasReservadas.put(51, "$");
        palavrasReservadas.put(16, "identificador");
        palavrasReservadas.put(0,"write");
        palavrasReservadas.put(1, "while");
        palavrasReservadas.put(2, "until");
        palavrasReservadas.put(3, "to");
        palavrasReservadas.put(4, "then");
        palavrasReservadas.put(6, "repeat");
        palavrasReservadas.put(7, "real");
        palavrasReservadas.put(8, "read");
        palavrasReservadas.put(9, "program");
        palavrasReservadas.put(10, "procedure");
        palavrasReservadas.put(11, "or");
        palavrasReservadas.put(12, "of");
        palavrasReservadas.put(15, "if");
        palavrasReservadas.put(18, "for");
        palavrasReservadas.put(19, "end");
        palavrasReservadas.put(20, "else");
        palavrasReservadas.put(21, "do");
        palavrasReservadas.put(22, "declaravariaveis");
        palavrasReservadas.put(13, "literal");
        palavrasReservadas.put(23, "const");
        palavrasReservadas.put(24, "char");
        palavrasReservadas.put(25, "chamaprocedure");
        palavrasReservadas.put(26, "begin");
        palavrasReservadas.put(27, "array");
        palavrasReservadas.put(28, "and");
        palavrasReservadas.put(14, "integer");
        palavrasReservadas.put(5, "string");
        palavrasReservadas.put(37, "inteiro");
        palavrasReservadas.put(36, "real");
        palavrasReservadas.put(31, "=");
        palavrasReservadas.put(30, ">");
        palavrasReservadas.put(34, "<");
        palavrasReservadas.put(29, ">=");
        palavrasReservadas.put(33, "<=");
        palavrasReservadas.put(32, "<>");
        palavrasReservadas.put(45, "..");
        palavrasReservadas.put(35, "+");
        palavrasReservadas.put(40, "]");
        palavrasReservadas.put(41, "[");
        palavrasReservadas.put(42, ";");
        palavrasReservadas.put(43, ":");
        palavrasReservadas.put(44, "/");
        palavrasReservadas.put(47, ",");
        palavrasReservadas.put(48, "*");
        palavrasReservadas.put(49, ")");
        palavrasReservadas.put(50, "(");
        palavrasReservadas.put(52, "-");
        palavrasReservadas.put(46, ".");
    }

    public static String get(Integer token) {
        return palavrasReservadas.get(token);
    }

}
