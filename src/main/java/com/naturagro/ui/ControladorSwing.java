package com.naturagro.ui;
import javax.swing.JFrame;

public class ControladorSwing {
    SwingCadastro cadastro = new SwingCadastro();
    SwingLogin login = new SwingLogin();
    SwingMenuPrincipal menuPrincipal = new SwingMenuPrincipal();
    SwingCadastroProdutos cadastroProdutos = new SwingCadastroProdutos();

    void abrirJanela(JFrame janela) {
        janela.setVisible(true);
    }



}
