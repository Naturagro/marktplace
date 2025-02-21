package com.naturagro.ui;

import com.naturagro.service.FuncionarioService;
import com.naturagro.ui.components.*;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {
    private static final long serialVersionUID = 1L;

    public static void startGui() {
        EventQueue.invokeLater(() -> {
            try {
                // Chama o mé_todo que configura todas as telas
                setupGui();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void setupGui() {
        // Cria um objeto para o controlador e serviço
        ControladorSwing controladorDeTela = new ControladorSwing();
        FuncionarioService funcionarioService = new FuncionarioService();

        // Criação de todas as telas
        SwingMenuPrincipal menuPrincipal = new SwingMenuPrincipal(controladorDeTela);
        SwingCadastro cadastro = new SwingCadastro(controladorDeTela, funcionarioService);
        SwingCadastroProdutos cadastroProdutos = new SwingCadastroProdutos(controladorDeTela);
        SwingControleEstoque controleEstoque = new SwingControleEstoque(controladorDeTela);
        SwingLogin login = new SwingLogin(controladorDeTela);
        SwingRelatorios relatorios = new SwingRelatorios(controladorDeTela);
        SwingVendas vendas = new SwingVendas(controladorDeTela);

        // Adiciona cada tela ao controlador
        controladorDeTela.adicionarJanela("login", login);
        controladorDeTela.adicionarJanela("menuPrincipal", menuPrincipal);
        controladorDeTela.adicionarJanela("cadastrar", cadastro);
        controladorDeTela.adicionarJanela("cadastroProdutos", cadastroProdutos);
        controladorDeTela.adicionarJanela("controleEstoque", controleEstoque);
        controladorDeTela.adicionarJanela("relatorios", relatorios);
        controladorDeTela.adicionarJanela("vendas", vendas);

        // Abre a janela de login
        controladorDeTela.abrirJanela("login");
    }
}
