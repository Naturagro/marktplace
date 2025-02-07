package com.naturagro.ui.components;

import com.naturagro.ui.ControladorSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingVendas extends JFrame {
	private static final long serialVersionUID = 1L;
	private ControladorSwing controlador;


	public SwingVendas(ControladorSwing controladorDeTela) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Menu Inicial");
		setBounds(0, 0, 1280, 720);


		// Painel principal
		JPanel PainelPrincipal = new JPanel();
		PainelPrincipal.setBackground(new Color(124, 188, 52));
		PainelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Padding
		setContentPane(PainelPrincipal);

		// GridBagLayout
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		PainelPrincipal.setLayout(gridBagLayout);


		// Componentes
		JLabel VendasLabel = new JLabel("Vendas");
		VendasLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		VendasLabel.setForeground(new Color(255, 255, 255));

		JButton BotaoAdcionar = new JButton("Adicionar");
		BotaoAdcionar.setBackground(new Color(133,179,58));
		BotaoAdcionar.setForeground(new Color(255,255,255));
		BotaoAdcionar.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

		JButton BotaoRemover = new JButton("Remover");
		BotaoRemover.setBackground(new Color(133,179,58));
		BotaoRemover.setForeground(new Color(255,255,255));
		BotaoRemover.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

		JButton BotaoFinalizar = new JButton("Finalizar");
		BotaoFinalizar.setBackground(new Color(133,179,58));
		BotaoFinalizar.setForeground(new Color(255,255,255));
		BotaoFinalizar.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

		// Botão Voltar
		JButton BotaoVoltar = new JButton("Voltar");
		BotaoVoltar.setBackground(new Color(133,179,58));
		BotaoVoltar.setForeground(new Color(255,255,255));
		BotaoVoltar.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		// Função do botão voltar
		BotaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDeTela.abrirJanela("menuPrincipal");
			}
		});

		// Tabela
		JTable table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);

		// Ajuste no GridBagConstraints
		// Título "Vendas"
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.insets = new Insets(10, 10, 10, 10);
		PainelPrincipal.add(VendasLabel, gbc);

		// Colocando botoes(com excecao do cancelar)
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		PainelPrincipal.add(BotaoAdcionar, gbc);

		gbc.gridx = 1;
		PainelPrincipal.add(BotaoRemover, gbc);

		gbc.gridx = 2;
		PainelPrincipal.add(BotaoFinalizar, gbc);

		// Colocando botao Voltar (foi colocado com uma configuração diferente dos outros )
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		PainelPrincipal.add(BotaoVoltar, gbc);
		// Função do botão Voltar

		// Colocando tabela dentro de um JScrollPane
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		PainelPrincipal.add(scrollPane, gbc);
	}
}
