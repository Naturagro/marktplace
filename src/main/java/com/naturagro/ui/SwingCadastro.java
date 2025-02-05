package com.naturagro.ui;

import com.naturagro.controllers.AccessControlController;
import com.naturagro.controllers.ControlException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingCadastro extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField RegisterUserTextField;
	private JPasswordField RegisterSenhaPasswordField, passwordField;

	public SwingCadastro(ControladorSwing controladorDeTela) {
		setTitle("Cadastro - Naturagro");
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

		RegisterUserTextField = new JTextField(30);
		RegisterUserTextField.setPreferredSize(new Dimension(202, 23));  // Definindo altura similar ao código de login
		RegisterUserTextField.setMinimumSize(new Dimension(202, 23));  // Tamanho mínimo igual
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		painelPrincipal.add(RegisterUserTextField, gbc);

		// Senha
		JLabel senhaLabel = new JLabel("Senha:");
		senhaLabel.setForeground(Color.WHITE);
		senhaLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.LINE_END;
		painelPrincipal.add(senhaLabel, gbc);

		RegisterSenhaPasswordField = new JPasswordField(30);
		RegisterSenhaPasswordField.setPreferredSize(new Dimension(202, 23));  // Ajuste para altura igual
		RegisterSenhaPasswordField.setMinimumSize(new Dimension(202, 23));  // Ajuste para altura igual
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		painelPrincipal.add(RegisterSenhaPasswordField, gbc);

		// Confirmar senha
		JLabel confirmarSenhaLabel = new JLabel("Confirmar Senha:");
		confirmarSenhaLabel.setForeground(Color.WHITE);  // Cor da fonte
		confirmarSenhaLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));  // Fonte maior
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.LINE_END;
		painelPrincipal.add(confirmarSenhaLabel, gbc);

		passwordField = new JPasswordField(20);
		passwordField.setPreferredSize(new Dimension(202, 23));  // Ajuste para altura igual
		passwordField.setMinimumSize(new Dimension(202, 23));  // Ajuste para altura igual
		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		painelPrincipal.add(passwordField, gbc);

		// Botão cadastro
		JButton cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.setBackground(new Color(124, 188, 52));
		cadastrarButton.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		painelPrincipal.add(cadastrarButton, gbc);
		// Ação do botão cadastro
		cadastrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// action listener pra pegar as informações preenchidas nos campos
				String nomeUser = SwingCadastro.this.RegisterUserTextField.getText();
				String password = String.valueOf(SwingCadastro.this.RegisterSenhaPasswordField.getPassword());
				String confirmacaopassword = String.valueOf(SwingCadastro.this.passwordField.getPassword());
				//try catch pra validar campos, e caso tenha algum erro, mostra uma janela com a exceção
				try {
					AccessControlController controller = new AccessControlController();
					controller.registerUser(nomeUser, password, confirmacaopassword);
					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
					controladorDeTela.abrirJanela(("login"));
				} catch (ControlException exception) {
					//vai mostrar o erro que foi tratado lá no access control
					JOptionPane.showMessageDialog(cadastrarButton, exception.getMessage());
				}
			}
		});
	}
	}