package com.naturagro.ui.components;

import com.naturagro.ui.ControladorSwing;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class SwingRelatorios extends JFrame {
	private static final long serialVersionUID = 1L;
	private ControladorSwing controlador;
	private JLabel backgroundLabel;

	// Tamanho mínimo, preferido e máximo para os componentes
	private final int fixedWidth = 400;

	public SwingRelatorios(ControladorSwing controladorDeTela) {
		this.controlador = controladorDeTela;

		// Configurações da janela
		setTitle("Relatórios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);

		// Painel principal com background customizado
		JPanel contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon background = new ImageIcon(getClass().getResource("/images/background2edit.png"));
				g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		contentPane.setBackground(new Color(124, 188, 52));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		gbc.anchor = GridBagConstraints.CENTER;

		// Logo
		ImageIcon logo = new ImageIcon(getClass().getResource("/images/logo.png"));
		JLabel logoLabel = new JLabel(logo);
		logoLabel.setMinimumSize(new Dimension(fixedWidth, 150));
		logoLabel.setPreferredSize(new Dimension(fixedWidth, 150));
		logoLabel.setMaximumSize(new Dimension(fixedWidth, 150));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		// Usamos HORIZONTAL para o caso de a janela aumentar, mas ele nunca ultrapassará fixedWidth
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0;
		contentPane.add(logoLabel, gbc);

		// Título
		JLabel relatoriosLabel = new JLabel("Relatórios", SwingConstants.CENTER);
		relatoriosLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
		relatoriosLabel.setForeground(Color.WHITE);
		relatoriosLabel.setMinimumSize(new Dimension(fixedWidth, 80));
		relatoriosLabel.setPreferredSize(new Dimension(fixedWidth, 80));
		relatoriosLabel.setMaximumSize(new Dimension(fixedWidth, 80));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(relatoriosLabel, gbc);

		// ComboBox de Seleção de Relatório
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBackground(new Color(83, 131, 5));
		comboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		comboBox.setForeground(Color.WHITE);
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
				"Movimentação de Estoque", "Produtos Mais Vendidos"
		}));
		comboBox.setMinimumSize(new Dimension(fixedWidth, 50));
		comboBox.setPreferredSize(new Dimension(fixedWidth, 50));
		comboBox.setMaximumSize(new Dimension(fixedWidth, 50));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(comboBox, gbc);

		// Campo de data com máscara
		try {
			MaskFormatter dateMask = new MaskFormatter("##/##/####");
			dateMask.setPlaceholderCharacter('_');
			dateMask.setAllowsInvalid(false);
			dateMask.setOverwriteMode(true);
			JFormattedTextField formattedTextField = new JFormattedTextField(dateMask);
			formattedTextField.setBackground(new Color(83, 131, 5));
			formattedTextField.setForeground(Color.WHITE);
			formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);
			formattedTextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
			formattedTextField.setText("__/__/____");
			formattedTextField.setMinimumSize(new Dimension(fixedWidth, 50));
			formattedTextField.setPreferredSize(new Dimension(fixedWidth, 50));
			formattedTextField.setMaximumSize(new Dimension(fixedWidth, 50));
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.gridwidth = 3;
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
		gerarRelatorioButton.setMinimumSize(new Dimension(fixedWidth, 60));
		gerarRelatorioButton.setPreferredSize(new Dimension(fixedWidth, 60));
		gerarRelatorioButton.setMaximumSize(new Dimension(fixedWidth, 60));
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(gerarRelatorioButton, gbc);

		gerarRelatorioButton.addActionListener(e -> {
			// TODO: pegar informações da data e do tipo de relatório
		});

		// Botão Voltar
		JButton botaoVoltar = new JButton("Voltar");
		botaoVoltar.setBackground(new Color(163, 43, 43));
		botaoVoltar.setForeground(Color.WHITE);
		botaoVoltar.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		botaoVoltar.setMinimumSize(new Dimension(fixedWidth, 60));
		botaoVoltar.setPreferredSize(new Dimension(fixedWidth, 60));
		botaoVoltar.setMaximumSize(new Dimension(fixedWidth, 60));
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(botaoVoltar, gbc);

		botaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.abrirJanela("menuPrincipal");
			}
		});

		contentPane.revalidate();
		contentPane.repaint();
	}
}
