package com.naturagro.ui.components;

import com.naturagro.controllers.AccessControlController;
import com.naturagro.controllers.ControlException;
import com.naturagro.models.Funcionario;
import com.naturagro.service.FuncionarioService;
import com.naturagro.ui.ControladorSwing;

import javax.swing.*;
import java.awt.*;

public class SwingCadastro extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField RegisterUserTextField;
	private JPasswordField RegisterSenhaPasswordField, passwordField;
	private JTextField CpfTextField;
	private ControladorSwing controladorDeTela;
	private FuncionarioService funcionarioService;

	public SwingCadastro(ControladorSwing controladorDeTela, FuncionarioService funcionarioService) {
		this.controladorDeTela = controladorDeTela;
		this.funcionarioService = funcionarioService;
		setTitle("Cadastro - Naturagro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
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
		RegisterUserTextField.setPreferredSize(new Dimension(202, 23));
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		painelPrincipal.add(RegisterUserTextField, gbc);

		// CPF
		JLabel CpfLabel = new JLabel("CPF:");
		CpfLabel.setForeground(Color.WHITE);
		CpfLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gbc.gridy = 3;
		gbc.gridx = 0;
		painelPrincipal.add(CpfLabel, gbc);

		CpfTextField = new JTextField(30);
		CpfTextField.setPreferredSize(new Dimension(202, 23));
		gbc.gridx = 1;
		painelPrincipal.add(CpfTextField, gbc);

		// Senha
		JLabel senhaLabel = new JLabel("Senha:");
		senhaLabel.setForeground(Color.WHITE);
		senhaLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gbc.gridy = 4;
		gbc.gridx = 0;
		painelPrincipal.add(senhaLabel, gbc);

		RegisterSenhaPasswordField = new JPasswordField(30);
		RegisterSenhaPasswordField.setPreferredSize(new Dimension(202, 23));
		gbc.gridx = 1;
		painelPrincipal.add(RegisterSenhaPasswordField, gbc);

		// Confirmar senha
		JLabel confirmarSenhaLabel = new JLabel("Confirmar Senha:");
		confirmarSenhaLabel.setForeground(Color.WHITE);
		confirmarSenhaLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gbc.gridy = 5;
		gbc.gridx = 0;
		painelPrincipal.add(confirmarSenhaLabel, gbc);

		passwordField = new JPasswordField(20);
		passwordField.setPreferredSize(new Dimension(202, 23));
		gbc.gridx = 1;
		painelPrincipal.add(passwordField, gbc);

		// ComboBox
		JComboBox<String> cargoComboBox = new JComboBox<>();
		cargoComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cargoComboBox.setForeground(Color.WHITE);
		cargoComboBox.setBackground(new Color(133, 179, 58));
		cargoComboBox.addItem("GERENTE");
		cargoComboBox.addItem("ESTOQUISTA");
		cargoComboBox.addItem("OPERADOR");
		gbc.gridy = 6;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		painelPrincipal.add(cargoComboBox, gbc);

		// Botão cadastro
		JButton cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.setBackground(new Color(124, 188, 52));
		cadastrarButton.setForeground(Color.WHITE);
		gbc.gridy = 7;
		painelPrincipal.add(cadastrarButton, gbc);

		cadastrarButton.addActionListener(e -> {
			String nomeUser = RegisterUserTextField.getText();
			String cpf = CpfTextField.getText();
			String password = String.valueOf(RegisterSenhaPasswordField.getPassword()).trim();
			String confirmacaopassword = String.valueOf(passwordField.getPassword()).trim();
			String cargoSelecionado = (String) cargoComboBox.getSelectedItem();
			try {
				AccessControlController controller = new AccessControlController();
				controller.registerUser(nomeUser, cpf, password, confirmacaopassword);
				String cpfTratado = cpf.replaceAll("[.\\- ]", "");
				funcionarioService.incluirAtomico(new Funcionario(nomeUser, cpfTratado, password, cargoSelecionado));
				JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				controladorDeTela.abrirJanela("menuPrincipal");
			} catch (ControlException exception) {
				JOptionPane.showMessageDialog(cadastrarButton, exception.getMessage());
			}
		});

		// Botão voltar
		JButton voltarButton = new JButton("Voltar");
		voltarButton.setBackground(new Color(168, 29, 29));
		voltarButton.setForeground(Color.WHITE);
		gbc.gridy = 8;
		painelPrincipal.add(voltarButton, gbc);

		voltarButton.addActionListener(e -> controladorDeTela.abrirJanela("menuPrincipal"));
	}
}
