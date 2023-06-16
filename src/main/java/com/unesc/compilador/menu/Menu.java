package com.unesc.compilador.menu;

import com.unesc.compilador.analisadorlexico.base.AnalisadorLexico;
import com.unesc.compilador.analisadorlexico.base.RegraLexaException;
import com.unesc.compilador.analisadorlexico.base.Token;
import com.unesc.compilador.analisadorsintatico.AnalisadorSintatico;
import com.unesc.compilador.analisadorsintatico.GramaticaCerta;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class Menu extends JFrame implements ActionListener {
    private final JTextArea textArea;
    private final JTextArea lines;
    private final JButton button;
    private final JScrollPane jsp;
    private final UndoManager undo = new UndoManager();
    private Resultado resultado;

    public Menu() {
        setTitle("Digite o código");
        jsp = new JScrollPane();
        textArea = new JTextArea();
        lines = new JTextArea("1");
        button = new JButton("Analisar");
        this.configure();
        setSize(800, 600);

        button.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(jsp, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);

        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button.setEnabled(false);
        try {
            List<Token> tokens = new AnalisadorLexico().analisar(textArea.getText());
            boolean valid = new AnalisadorSintatico().analisar(new GramaticaCerta(), tokens);
            System.out.println("Gramática válida: " + valid);
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

    private void configure() {
        lines.setBackground(Color.LIGHT_GRAY);
        lines.setEditable(false);
        Document doc = textArea.getDocument();
        doc.addUndoableEditListener(evt -> undo.addEdit(evt.getEdit()));
        textArea.getActionMap().put("Undo", new AbstractAction("Undo") {
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (undo.canUndo()) {
                        undo.undo();
                    }
                } catch (CannotUndoException e) {
                }
            }
        });
        textArea.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            public String getText() {
                int caretPosition = textArea.getDocument().getLength();
                Element root = textArea.getDocument().getDefaultRootElement();
                String text = "1" + System.getProperty("line.separator");
                for(int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                    text += i + System.getProperty("line.separator");
                }
                return text;
            }
            @Override
            public void changedUpdate(DocumentEvent de) {
                lines.setText(getText());
            }
            @Override
            public void insertUpdate(DocumentEvent de) {
                lines.setText(getText());
            }
            @Override
            public void removeUpdate(DocumentEvent de) {
                lines.setText(getText());
            }
        });
        jsp.getViewport().add(textArea);
        jsp.setRowHeaderView(lines);
        add(jsp);
    }
}