package com.unesc.compilador.menu;

import com.unesc.compilador.analisadorlexico.base.Token;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Resultado extends JFrame {
    private JPanel panel;
    private JTable tableResultado;
    private JScrollPane scrollPane;

    public Resultado(List<Token> tokens) {
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{ "Linha", "Token", "Código" }, 0);
        tableResultado.setModel(tableModel);

        tokens.forEach(token -> tableModel.addRow(new Object[]{
                token.getLinha(),
                token.getToken(),
                token.getCodigo()
        }));

        setTitle("Resultado da análise");
        setLocationRelativeTo(null);
        setContentPane(panel);
        pack();
        setVisible(true);
    }
}
