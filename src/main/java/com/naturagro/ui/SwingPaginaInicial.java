package com.naturagro.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingPaginaInicial extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JLabel label;

    // Criando a Tela
    public SwingPaginaInicial() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Página Inicial");

        // Localização e Tamanho
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        // Definição de Layout
        getContentPane().setLayout(new GridLayout(6, 7, 10, 10));

        // Forçar tela cheia
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        // Criando um "Header"
        JPanel header = new JPanel();
        header.setBounds(0, 0, 1920, 90);
        header.setBackground(Color.black);

        getContentPane().add(header);
    }
}
