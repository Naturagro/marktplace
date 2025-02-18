package com.naturagro.ui;

import com.naturagro.models.Funcionario;
import com.naturagro.ui.components.SwingMenuPrincipal;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;


public class ControladorSwing {
    // HashMap tem valores String e JFrame, servindo pra identificar o frame e setar ele como visivel
    private Map<String, JFrame> janelas = new HashMap<>();
    private JFrame janelaAtual;
    private String tipoUsuario; // Variável para armazenar o tipo de usuário
    private Funcionario funcionarioLogado;

    // Método para adicionar todas as Janelas existentes no main
    public void adicionarJanela(String nome, JFrame janela) {
        janelas.put(nome, janela);
    }

    // Método para trocar as janelas durante a execução do programa
    public void abrirJanela(String nome) {
        // Se a variável já tiver uma janela (ou seja, se o programa já tiver mostrando algo), ele esconderá a janela atual
        if (janelaAtual != null) {
            janelaAtual.setVisible(false);
        }
        // Depois define o nome da janela que foi adicionada no main como parâmetro
        janelaAtual = janelas.get(nome);

        // Abre a janela
        if (janelaAtual != null) {
            // Passa o tipo de usuário para a tela de menu principal
            if (janelaAtual instanceof SwingMenuPrincipal) {
                ((SwingMenuPrincipal) janelaAtual).atualizarTipoUsuario(tipoUsuario);
            }
            janelaAtual.setVisible(true);
        }
    }

    // Méto_do para definir o tipo de usuário
    public void definirTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    // Méto_do para obter o tipo de usuário
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    // Méto_do para limpar o campo de texto
    public void limparCampo(JTextField campo) {
        campo.setText("");
    }

    public void definirFuncionarioLogado(Funcionario funcionarioLogado) {this.funcionarioLogado = funcionarioLogado; }

    public Funcionario getFuncionarioLogado() {return funcionarioLogado; }
}
