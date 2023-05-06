package com.unesc.compilador.menu;

import com.unesc.compilador.analisadorlexico.base.AnalisadorLexico;
import com.unesc.compilador.analisadorlexico.base.RegraLexaException;
import com.unesc.compilador.analisadorlexico.base.Token;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class Menu extends JFrame implements ActionListener {
    private final JTextArea textArea;
    private final JButton button;

    public Menu() {
        super("Digite o código");

        textArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);

        button = new JButton("Analisar");
        button.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);

        setContentPane(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button.setEnabled(false);
        try {
            List<Token> tokens = new AnalisadorLexico().analisar(textArea.getText());
            new Resultado(tokens);
        } catch (RegraLexaException exception) {
            String mensagem = String.format("Linha: %d\nToken: %s\nMensagem: %s",
                    exception.getLinha(), exception.getToken(), exception.getMessage());
            JOptionPane.showMessageDialog(this, mensagem, "Erro léxico", JOptionPane.ERROR_MESSAGE);
            exception.printStackTrace();
        }
        finally {
            button.setEnabled(true);
        }
    }
}