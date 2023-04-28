package com.unesc.compilador;

import com.unesc.compilador.analisadorlexico.base.AnalisadorLexico;
import com.unesc.compilador.analisadorlexico.base.Token;
import com.unesc.compilador.menu.Menu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        new Menu();
    }
}
