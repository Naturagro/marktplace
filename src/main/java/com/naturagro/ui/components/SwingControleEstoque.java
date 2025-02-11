package com.naturagro.ui.components;

import com.naturagro.ui.ControladorSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingControleEstoque extends JFrame {
	private static final long serialVersionUID = 1L;
	private ControladorSwing controlador;
	private JLabel backgroundLabel;

	// Tela
	public SwingControleEstoque(ControladorSwing controladorDeTela) {
		this.controlador = controladorDeTela;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Controle de Estoque");
		setBounds(0, 0, 1280, 720);

		// Painel principal com bordas verdes e fundo transparente
		JPanel PainelPrincipal = new JPanel(new GridBagLayout());
		PainelPrincipal.setBackground(new Color(124, 188, 52)); // Cor verde
		PainelPrincipal.setOpaque(true); // Garantir que o fundo seja preenchido com verde
		setContentPane(PainelPrincipal);

		// Configuração do layout do painel de conteúdo
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		// Adicionando imagem de fundo
		ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/images/background2edit.png"));
		backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 4;
		PainelPrincipal.add(backgroundLabel, gbc);

		// Painel de componentes (sobrepondo a imagem de fundo)
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);  // Tornar o painel transparente para ver a imagem de fundo
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		backgroundLabel.add(panel, gbc);

		GridBagConstraints innerGbc = new GridBagConstraints();
		innerGbc.insets = new Insets(10, 10, 10, 10);
		innerGbc.fill = GridBagConstraints.BOTH;
		innerGbc.weightx = 1.0;
		innerGbc.weighty = 1.0;

		// Título "Controle de Estoque"
		JLabel ControleEstoqueLabel = new JLabel("Controle de Estoque");
		ControleEstoqueLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		ControleEstoqueLabel.setForeground(new Color(255, 255, 255)); // Cor branca para contraste
		innerGbc.gridx = 0;
		innerGbc.gridy = 0;
		innerGbc.gridwidth = 4;
		panel.add(ControleEstoqueLabel, innerGbc);

		// Botões
		JButton AdicionarButton = new JButton("Adicionar");
		AdicionarButton.setBackground(new Color(83, 131, 5));
		AdicionarButton.setForeground(new Color(255, 255, 255));
		AdicionarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

		JButton RemoverButton = new JButton("Remover");
		RemoverButton.setForeground(Color.WHITE);
		RemoverButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		RemoverButton.setBackground(new Color(83, 131, 5));

		JButton BotaoVoltar = new JButton("Voltar");
		BotaoVoltar.setForeground(Color.WHITE);
		BotaoVoltar.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		BotaoVoltar.setBackground(new Color(83, 131, 5));

		// Função do botão Voltar
		BotaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDeTela.abrirJanela("menuPrincipal");
			}
		});

		// Tabela
		JTable EstoqueTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(EstoqueTable);

		// Colocando os botões
		innerGbc.gridwidth = 1;
		innerGbc.gridx = 0;
		innerGbc.gridy = 1;
		panel.add(AdicionarButton, innerGbc);

		innerGbc.gridx = 1;
		panel.add(RemoverButton, innerGbc);

		innerGbc.gridx = 2;
		innerGbc.weightx = 1;
		panel.add(BotaoVoltar, innerGbc);

		// Colocando a tabela dentro de um JScrollPane
		innerGbc.gridx = 0;
		innerGbc.gridy = 2;
		innerGbc.gridwidth = 4;
		innerGbc.fill = GridBagConstraints.BOTH;
		innerGbc.weightx = 1;
		innerGbc.weighty = 1;
		panel.add(scrollPane, innerGbc);

		// Listener para redimensionar a imagem de fundo quando a janela for redimensionada
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent componentEvent) {
				Image img = backgroundIcon.getImage();
				Image newImg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
				backgroundLabel.setIcon(new ImageIcon(newImg));
			}
		});

		// Centralizando a tela
		setLocationRelativeTo(null);
	}
}
