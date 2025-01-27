package com.naturagro.ui;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            // Todos os testes do swing devem ser realizados aqui
            public void run() {
                try {
                    // Instancie o objeto com a classe da tela aqui
                    SwingCredencial credencial = new SwingCredencial();
                    SwingPaginaInicial inicial = new SwingPaginaInicial();
                    // Abrir ou não a tela
                    credencial.setVisible(false);
                    inicial.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
