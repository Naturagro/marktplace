package com.naturagro.ui.components;

import com.naturagro.ui.ControladorSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class SwingRelatorios extends JFrame {
	private static final long serialVersionUID = 1L;
	private ControladorSwing controlador;
	private JLabel backgroundLabel;

	// Tela
	public SwingRelatorios(ControladorSwing controladorDeTela) {
		this.controlador = controladorDeTela;

		// Configurações da janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Relatórios");
		setSize(900, 600);

		// Centralizar a janela na tela
		setLocationRelativeTo(null);

		// Painel principal
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(124, 188, 52));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);  // Espaçamento entre os componentes
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Logo
		ImageIcon logo = new ImageIcon(getClass().getResource("/images/logo.png"));
		JLabel logoLabel = new JLabel(logo);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		contentPane.add(logoLabel, gbc);

		// Título
		JLabel relatoriosLabel = new JLabel("Relatórios");
		relatoriosLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		relatoriosLabel.setForeground(Color.WHITE);
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		contentPane.add(relatoriosLabel, gbc);

		// ComboBox de Seleção de Relatório
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBackground(new Color(83, 131, 5));
		comboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		comboBox.setForeground(Color.WHITE);
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Movimentação de Estoque", "Produtos Mais Vendidos"}));
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(comboBox, gbc);

		// Campo de Data
		JLabel dataLabel = new JLabel("Data:");
		dataLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		dataLabel.setForeground(Color.WHITE);
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		contentPane.add(dataLabel, gbc);

		try {
			// Mascara de data
			MaskFormatter dateMask = new MaskFormatter("##/##/####");
			dateMask.setPlaceholderCharacter('_');
			dateMask.setAllowsInvalid(false);
			dateMask.setOverwriteMode(true);

			JFormattedTextField formattedTextField = new JFormattedTextField(dateMask);
			formattedTextField.setBackground(new Color(83, 131, 5));
			formattedTextField.setForeground(Color.WHITE);
			formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);
			formattedTextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
			formattedTextField.setText("__/__/__");
			gbc.gridx = 2;
			gbc.gridy = 3;
			gbc.gridwidth = 2;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			contentPane.add(formattedTextField, gbc);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Botão Gerar Relatório
		JButton gerarRelatorioButton = new JButton("Gerar Relatório");
		gerarRelatorioButton.setBackground(new Color(83, 131, 5));
		gerarRelatorioButton.setForeground(Color.WHITE);
		gerarRelatorioButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.gridwidth = 1;  // Botão ocupa toda a largura
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(gerarRelatorioButton, gbc);

		gerarRelatorioButton.addActionListener(e -> {
			//todo: pegar informações da data e do tipo de relatório
		});

		// Botão Voltar
		JButton botaoVoltar = new JButton("Voltar");
		botaoVoltar.setForeground(Color.WHITE);
		botaoVoltar.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		botaoVoltar.setBackground(new Color(168, 29, 29));
		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.gridwidth = 1;  // Botão ocupa toda a largura
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(botaoVoltar, gbc);

		// Função do botão voltar
		botaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDeTela.abrirJanela("menuPrincipal");
			}
		});

		// Adicionando a imagem de fundo
		ImageIcon background2 = new ImageIcon(getClass().getResource("/images/background2edit.png"));
		backgroundLabel = new JLabel(background2);
		contentPane.add(backgroundLabel, new GridBagConstraints() {{
			gridx = 0;
			gridy = 0;
			gridwidth = 3;
			gridheight = 6;
			weightx = 1.0;
			weighty = 1.0;
			fill = GridBagConstraints.BOTH;
		}});

		// Listener para redimensionar a imagem
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent componentEvent) {
				Image img = background2.getImage();
				Image newImg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
				backgroundLabel.setIcon(new ImageIcon(newImg));
			}
		});

		// Ajustar o tamanho da imagem para preencher toda a tela
		contentPane.repaint();
	}
}
