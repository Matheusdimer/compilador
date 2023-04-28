package com.unesc.compilador.menu;

import com.unesc.compilador.analisadorlexico.base.AnalisadorLexico;
import com.unesc.compilador.analisadorlexico.base.Token;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Menu extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JButton button;

    public Menu() {
        super("Digite o código");

        // Cria o JTextArea e adiciona uma barra de rolagem vertical
        textArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Cria o botão e adiciona o listener para o evento de clique
        button = new JButton("Analisar");
        button.addActionListener(this);

        // Adiciona os componentes ao painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);

        // Adiciona o painel principal ao JFrame
        setContentPane(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button.setEnabled(false);
        List<Token> analises = new AnalisadorLexico().analisar(textArea.getText());
        System.out.println(analises);
        button.setEnabled(true);
    }
}