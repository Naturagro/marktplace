package com.naturagro.ui.components;

import com.naturagro.ui.ControladorSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingControleEstoque extends JFrame {
	private static final long serialVersionUID = 1L;
	private ControladorSwing controlador;

	// Tela
	public SwingControleEstoque(ControladorSwing controladorDeTela) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Controle de Estoque");
		setBounds(0, 0, 1280, 720);

		// Painel principal
		JPanel PainelPrincipal = new JPanel();
		PainelPrincipal.setBackground(new Color(124, 188, 52));
		PainelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
		setContentPane(PainelPrincipal);

		// GridBagLayout
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		PainelPrincipal.setLayout(gridBagLayout);

		// Componentes
		JLabel ControleEstoqueLabel = new JLabel("Controle de Estoque");
		ControleEstoqueLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		ControleEstoqueLabel.setForeground(new Color(255, 255, 255));

		JButton AdicionarButton = new JButton("Adicionar");
		AdicionarButton.setBackground(new Color(133, 179, 58));
		AdicionarButton.setForeground(new Color(255, 255, 255));
		AdicionarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

		JButton RemoverButton = new JButton("Remover");
		RemoverButton.setForeground(Color.WHITE);
		RemoverButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		RemoverButton.setBackground(new Color(133, 179, 58));

		// Botão Voltar
		JButton BotaoVoltar = new JButton("Voltar");
		BotaoVoltar.setForeground(Color.WHITE);
		BotaoVoltar.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		BotaoVoltar.setBackground(new Color(133, 179, 58));
		// Função do botão Voltar
		BotaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDeTela.abrirJanela("menuPrincipal");
			}
		});

		// Tabela
		JTable EstoqueTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(EstoqueTable);

		// Ajuste no GridBagConstraints
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.insets = new Insets(10, 10, 10, 10);
		PainelPrincipal.add(ControleEstoqueLabel, gbc);

		// Colocando os botões
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		PainelPrincipal.add(AdicionarButton, gbc);

		gbc.gridx = 1;
		PainelPrincipal.add(RemoverButton, gbc);

		gbc.gridx = 2;
		gbc.weightx = 1;
		PainelPrincipal.add(BotaoVoltar, gbc);

		// Colocando a tabela dentro de um JScrollPane
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		PainelPrincipal.add(scrollPane, gbc);

		// Centralizando a tela
		setLocationRelativeTo(null);
	}
}
