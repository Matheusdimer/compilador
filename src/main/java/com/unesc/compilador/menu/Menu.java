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
    private JTextArea textArea;
    private JButton button;

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
            List<Token> analises = new AnalisadorLexico().analisar(textArea.getText());
            JOptionPane.showMessageDialog(this, analises.stream()
                    .map(Token::toString)
                    .collect(Collectors.joining("\n")));
        } catch (RegraLexaException exception) {
            String mensagem = String.format("Erro lexo:\nLinha: %d\nToken: %d\nMensagem: %s",
                    exception.getLinha(), exception.getToken(), exception.getMessage());
            JOptionPane.showMessageDialog(this, mensagem);
            exception.printStackTrace();
        }
        finally {
            button.setEnabled(true);
        }
    }
}