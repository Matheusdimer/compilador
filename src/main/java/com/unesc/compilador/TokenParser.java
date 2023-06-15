package com.unesc.compilador;

import java.util.HashMap;
import java.util.Map;

public class TokenParser {

    private static final Map<Integer,String> palavrasReservadas = new HashMap<>();

    static  {
        palavrasReservadas.put(1, "write");
        palavrasReservadas.put(2, "while");
        palavrasReservadas.put(3, "until");
        palavrasReservadas.put(4, "to");
        palavrasReservadas.put(5, "then");
        palavrasReservadas.put(6, "string");
        palavrasReservadas.put(7, "repeat");
        palavrasReservadas.put(8, "real");
        palavrasReservadas.put(9, "read");
        palavrasReservadas.put(10, "program");
        palavrasReservadas.put(11, "procedure");
        palavrasReservadas.put(12, "or");
        palavrasReservadas.put(13, "of");
        palavrasReservadas.put(14, "literal");
        palavrasReservadas.put(15, "integer");
        palavrasReservadas.put(16, "if");
        palavrasReservadas.put(17, "identificador");
        palavrasReservadas.put(18, "î");
        palavrasReservadas.put(19, "for");
        palavrasReservadas.put(20, "end");
        palavrasReservadas.put(21, "else");
        palavrasReservadas.put(22, "do");
        palavrasReservadas.put(23, "declaravariaveis");
        palavrasReservadas.put(24, "const");
        palavrasReservadas.put(25, "char");
        palavrasReservadas.put(26, "chamaprocedure");
        palavrasReservadas.put(27, "begin");
        palavrasReservadas.put(28, "array");
        palavrasReservadas.put(29, "and");
        palavrasReservadas.put(30, ">=");
        palavrasReservadas.put(31, ">");
        palavrasReservadas.put(32, "=");
        palavrasReservadas.put(33, "<>");
        palavrasReservadas.put(34, "<=");
        palavrasReservadas.put(35, "<");
        palavrasReservadas.put(36, "+");
        palavrasReservadas.put(37, "numreal");
        palavrasReservadas.put(38, "numinteiro");
        palavrasReservadas.put(39, "nomestring");
        palavrasReservadas.put(40, "nomechar");
        palavrasReservadas.put(41, "]");
        palavrasReservadas.put(42, "[");
        palavrasReservadas.put(43, ";");
        palavrasReservadas.put(44, ":");
        palavrasReservadas.put(45, "/");
        palavrasReservadas.put(46, "..");
        palavrasReservadas.put(47, ".");
        palavrasReservadas.put(48, ",");
        palavrasReservadas.put(49, "*");
        palavrasReservadas.put(50, ")");
        palavrasReservadas.put(51, "(");
        palavrasReservadas.put(52, "$");
        palavrasReservadas.put(53, "-");
    }

    public static String get(Integer token) {
        return palavrasReservadas.getOrDefault(token, String.valueOf(token));
    }

}
