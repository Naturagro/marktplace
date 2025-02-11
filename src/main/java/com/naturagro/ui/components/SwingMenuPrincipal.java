package com.naturagro.ui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.naturagro.ui.ControladorSwing;

public class SwingMenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ControladorSwing controlador;
	private JLabel backgroundLabel;

	public SwingMenuPrincipal(ControladorSwing controlador) {
		this.controlador = controlador;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Menu Principal");
		setSize(1280, 720);
		setLocationRelativeTo(null);

		contentPane = new JPanel(new GridBagLayout());
		contentPane.setBackground(new Color(124, 188, 52));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		// Fundo (imagem que acompanha o redimensionamento)
		backgroundLabel = new JLabel(new ImageIcon(getClass().getResource("/images/background2edit.png")));
		backgroundLabel.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 4;
		contentPane.add(backgroundLabel, gbc);

		// Painel de componentes
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);
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

		// Logo
		ImageIcon logo = new ImageIcon(getClass().getResource("/images/logo.png"));
		JLabel logoLabel = new JLabel(logo);
		innerGbc.gridx = 0;
		innerGbc.gridy = 0;
		innerGbc.gridwidth = 1;
		innerGbc.anchor = GridBagConstraints.WEST;
		panel.add(logoLabel, innerGbc);

		// Título
		JLabel menuLabel = new JLabel("Menu Principal");
		menuLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
		menuLabel.setForeground(Color.WHITE);
		innerGbc.gridx = 1;
		innerGbc.gridy = 0;
		innerGbc.gridwidth = 3;
		innerGbc.anchor = GridBagConstraints.CENTER;
		panel.add(menuLabel, innerGbc);

		// Botões
		String[] botoes = {"Cadastro de Produtos", "Vendas", "Controle de Estoque", "Relatórios", "Cadastrar", "Sair"};
		String[] acoes = {"cadastroProdutos", "vendas", "controleEstoque", "relatorios", "cadastrar", "sair"};

		innerGbc.gridwidth = 1;
		innerGbc.anchor = GridBagConstraints.CENTER;
		innerGbc.fill = GridBagConstraints.BOTH;

		for (int i = 0; i < botoes.length; i++) {
			JButton button = new JButton(botoes[i]);
			button.setBackground(new Color(96, 145, 2));
			button.setForeground(Color.WHITE);
			button.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));  // Tamanho da fonte reduzido
			button.setPreferredSize(new Dimension(200, 30));  // Tamanho fixo para os botões
			button.addActionListener(new BotaoListener(acoes[i]));

			innerGbc.gridx = i % 2;
			innerGbc.gridy = (i / 2) + 1;
			panel.add(button, innerGbc);
		}

		// Listener para redimensionar a imagem
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent componentEvent) {
				ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/background2edit.png"));
				Image img = imageIcon.getImage();
				Image newImg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
				backgroundLabel.setIcon(new ImageIcon(newImg));
			}
		});
	}

	private class BotaoListener implements ActionListener {
		private String acao;

		public BotaoListener(String acao) {
			this.acao = acao;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			controlador.abrirJanela(acao);
		}
	}
}
