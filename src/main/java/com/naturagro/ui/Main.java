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
                    SwingCadastroProdutos cadastroProduto = new SwingCadastroProdutos();
                    // Abrir ou não a tela
                    cadastroProduto.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
