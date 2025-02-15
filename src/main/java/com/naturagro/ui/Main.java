package com.naturagro.ui;

import com.naturagro.controllers.AccessControlController;
import com.naturagro.service.FuncionarioService;
import com.naturagro.ui.components.*;

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
                    ControladorSwing controladorDeTela = new ControladorSwing();
                    SwingMenuPrincipal menuPrincipal = new SwingMenuPrincipal(controladorDeTela);
                    FuncionarioService funcionarioService = new FuncionarioService();
                    SwingCadastro cadastro = new SwingCadastro(controladorDeTela, funcionarioService);
                    SwingCadastroProdutos cadastroProdutos = new SwingCadastroProdutos(controladorDeTela);
                    SwingControleEstoque controleEstoque = new SwingControleEstoque(controladorDeTela);
                    SwingLogin login = new SwingLogin(controladorDeTela);
                    SwingRelatorios relatorios = new SwingRelatorios(controladorDeTela);
                    SwingVendas vendas = new SwingVendas(controladorDeTela);

                    // Adiciona cada tela ao objeto controlador
                    controladorDeTela.adicionarJanela("login",login);
                    controladorDeTela.adicionarJanela("menuPrincipal",menuPrincipal);
                    controladorDeTela.adicionarJanela("cadastrar", cadastro);
                    controladorDeTela.adicionarJanela("cadastroProdutos", cadastroProdutos);
                    controladorDeTela.adicionarJanela("controleEstoque", controleEstoque);
                    controladorDeTela.adicionarJanela("relatorios", relatorios);
                    controladorDeTela.adicionarJanela("vendas", vendas);

                    // Inicie a tela colocando o nome da tela que você quer na String
                     controladorDeTela.abrirJanela("vendas");


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
