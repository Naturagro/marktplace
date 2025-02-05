package com.naturagro.ui;

import com.naturagro.controllers.AccessControlController;

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
                    SwingCadastro cadastro = new SwingCadastro(controladorDeTela);
                    SwingCadastroProdutos cadastroProdutos = new SwingCadastroProdutos(controladorDeTela);
                    SwingControleEstoque controleEstoque = new SwingControleEstoque(controladorDeTela);
                    SwingLogin login = new SwingLogin(controladorDeTela);
                    SwingRelatorios relatorios = new SwingRelatorios(controladorDeTela);
                    SwingVendas vendas = new SwingVendas(controladorDeTela);

                    // Adiciona cada tela ao objeto controlador
                    controladorDeTela.adicionarJanela("login",login);
                    controladorDeTela.adicionarJanela("menuPrincipal",menuPrincipal);
                    controladorDeTela.adicionarJanela("cadastro", cadastro);
                    controladorDeTela.adicionarJanela("cadastroProdutos", cadastroProdutos);
                    controladorDeTela.adicionarJanela("controleEstoque", controleEstoque);
                    controladorDeTela.adicionarJanela("relatorios", relatorios);
                    controladorDeTela.adicionarJanela("vendas", vendas);

                    // Se quiser apenas testar uma tela especifica, comente a linha 41 até a linha 46 e descomente a linha 38
                    // Inicie a tela colocando o nome da tela que você quer na String
                    // controladorDeTela.abrirJanela("cadastro");

                    // Faz a checagem se é o primeiro cadastro do banco de dados, e a depender, redireciona pra tela certa
                    AccessControlController controladorDeAcesso = new AccessControlController();
                    if (controladorDeAcesso.isFirstRegister()) {
                        controladorDeTela.abrirJanela("cadastro");
                    } else {
                        controladorDeTela.abrirJanela("login");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
