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
                    // Cria um objeto para cada tela
                    ControladorSwing controlador = new ControladorSwing();
                    SwingMenuPrincipal menuPrincipal = new SwingMenuPrincipal(controlador);
                    SwingCadastro cadastro = new SwingCadastro(controlador);
                    SwingCadastroProdutos cadastroProdutos = new SwingCadastroProdutos(controlador);
                    SwingControleEstoque controleEstoque = new SwingControleEstoque(controlador);
                    SwingLogin login = new SwingLogin(controlador);
                    SwingRelatorios relatorios = new SwingRelatorios(controlador);
                    SwingVendas vendas = new SwingVendas(controlador);

                    // Adiciona cada tela ao objeto controlador
                    controlador.adicionarJanela("login",login);
                    controlador.adicionarJanela("menuPrincipal",menuPrincipal);
                    controlador.adicionarJanela("cadastro", cadastro);
                    controlador.adicionarJanela("cadastroProdutos", cadastroProdutos);
                    controlador.adicionarJanela("controleEstoque", controleEstoque);
                    controlador.adicionarJanela("relatorios", relatorios);
                    controlador.adicionarJanela("vendas", vendas);

                    // Inicia já com a tela de login
                    controlador.abrirJanela("login");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
