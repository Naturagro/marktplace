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
                	SwingCadastro cadastro = new SwingCadastro();
                    SwingRelatorios relatorio = new SwingRelatorios();
                	SwingCadastroProdutos cadastroProdutos = new SwingCadastroProdutos();
                	SwingVendas vendas = new SwingVendas();
                	SwingControleEstoque estoque = new SwingControleEstoque();
                	SwingLogin login = new SwingLogin();
                	SwingMenuPrincipal menu = new SwingMenuPrincipal();
                             
                    // Abrir ou não a tela
                    relatorio.setVisible(true);
                    cadastro.setVisible(true);
                    cadastroProdutos.setVisible(true);
                    vendas.setVisible(true);
                    estoque.setVisible(true);
                    login.setVisible(true);
                    menu.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
