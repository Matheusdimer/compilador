package com.unesc.compilador;

import com.unesc.compilador.analisadorlexico.base.AnalisadorLexico;
import com.unesc.compilador.analisadorlexico.base.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        AnalisadorLexico analisadorLexico = new AnalisadorLexico();
        String codigo = Files.readString(Path.of("./src/main/resources/codigo.txt"));

        List<Token> resultado = analisadorLexico.analisar(codigo);
        resultado.forEach(System.out::println);
    }
}
