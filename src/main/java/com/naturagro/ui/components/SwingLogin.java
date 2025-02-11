package com.naturagro.ui.components;

import com.naturagro.ui.ControladorSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField LoginUserTextField;
	private JPasswordField LoginSenhaPasswordField;

	public SwingLogin(ControladorSwing controladorDeTela) {
		setTitle("Login - Naturagro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(630, 500);
		setLocationRelativeTo(null);

		JPanel painelPrincipal = new JPanel() {
			private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon background = new ImageIcon(getClass().getResource("/images/background1edit.png"));
				g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		painelPrincipal.setLayout(new GridBagLayout());
		painelPrincipal.setBackground(new Color(112, 140, 52));
		setContentPane(painelPrincipal);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Logo
		ImageIcon logo = new ImageIcon(getClass().getResource("/images/logo.png"));
		JLabel logoLabel = new JLabel(logo);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		painelPrincipal.add(logoLabel, gbc);

		// Título
		JLabel titleLabel = new JLabel("Naturagro™");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 36));
		titleLabel.setForeground(Color.WHITE);
		gbc.gridy = 1;
		painelPrincipal.add(titleLabel, gbc);

		// Usuário
		JLabel usuarioLabel = new JLabel("Usuário:");
		usuarioLabel.setForeground(Color.WHITE);
		usuarioLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridy = 2;
		painelPrincipal.add(usuarioLabel, gbc);

		LoginUserTextField = new JTextField(30);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		LoginUserTextField.setMinimumSize(new Dimension(200, 23));
		LoginUserTextField.setPreferredSize(new Dimension(250, 23));
		painelPrincipal.add(LoginUserTextField, gbc);

		// Senha
		JLabel senhaLabel = new JLabel("Senha:");
		senhaLabel.setForeground(Color.WHITE);
		senhaLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.LINE_END;
		painelPrincipal.add(senhaLabel, gbc);

		LoginSenhaPasswordField = new JPasswordField(30);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		LoginSenhaPasswordField.setMinimumSize(new Dimension(200, 23));
		LoginSenhaPasswordField.setPreferredSize(new Dimension(250, 23));
		painelPrincipal.add(LoginSenhaPasswordField, gbc);

		// Botão login
		JButton loginButton = new JButton("Entrar");
		loginButton.setBackground(new Color(124, 188, 52));
		loginButton.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		painelPrincipal.add(loginButton, gbc);
		// Ação do botão login
		JButton LoginJButton = new JButton("Entrar");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDeTela.abrirJanela("menuPrincipal");
			}
		});
	}

}
