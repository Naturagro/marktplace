package com.naturagro.ui;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ControladorSwing {
    // HashMap tem valores String e JFrame, servindo pra identificar o frame e setar ele como visivel
    private Map<String, JFrame> janelas = new HashMap<>();
    private JFrame janelaAtual;

    // Método para adicionar todas as Janelas existentes no main
    public void adicionarJanela(String nome, JFrame janela) {
        janelas.put(nome, janela);
    }

    // Método para trocar as janelas durante a execução do programa
    public void abrirJanela(String nome) {
        // se a variavel já tiver uma janela (ou seja, se o programa já tiver mostrando algo) ele escondera a janela atual
        if (janelaAtual != null) {
            janelaAtual.setVisible(false);
        }
        // depois define o nome da janela que foi adicionada no main como parametro
        janelaAtual = janelas.get(nome);

        // Abre a janela
        if (janelaAtual != null) {
            janelaAtual.setVisible(true);
        }
    }

    public void limparCampo(JTextField campo) {
       campo.setText("");
    }

}
