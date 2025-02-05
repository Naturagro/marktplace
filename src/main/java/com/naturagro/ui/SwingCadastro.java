package com.naturagro.ui;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.naturagro.controllers.AccessControlController;
import com.naturagro.controllers.ControlException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SwingCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField RegisterUserTextField;
	private JPasswordField RegisterSenhaPasswordField;
	private JPasswordField passwordField;
	private ControladorSwing controlador;

	public SwingCadastro(ControladorSwing controlador) {

		setBackground(new Color(112, 140, 52));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 630, 390);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 140, 52));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane camadas = new JLayeredPane();
		contentPane.add(camadas);
		camadas.setBounds(0,0,614,351);
		
		JLabel UserLabel = new JLabel("Usuário:");
		UserLabel.setForeground(new Color(255, 255, 255));
		UserLabel.setFont(new Font("Tekton Pro", Font.PLAIN, 25));
		UserLabel.setSize(88, 30);
		UserLabel.setLocation(156, 198);
		camadas.add(UserLabel, Integer.valueOf(1));
		
		ImageIcon background1 = new ImageIcon(getClass().getResource("/images/background1edit.png"));
		JLabel backgroundLabel = new JLabel(background1);
		backgroundLabel.setBounds(0, 0, 614, 351);
		camadas.add(backgroundLabel,Integer.valueOf(0));

		// Nota: Se eu tiver MUITA coragem, eu crio uma subclasse de JTextField e faço uma classe onde o text field tem border radius
		RegisterUserTextField = new JTextField();
		RegisterUserTextField.setBounds(248, 199, 202, 20);
		camadas.add(RegisterUserTextField, Integer.valueOf(1));
		
		JLabel PassWordLabel = new JLabel("Senha:");
		PassWordLabel.setSize(70, 30);
		PassWordLabel.setLocation(169, 230);
		PassWordLabel.setForeground(Color.WHITE);
		PassWordLabel.setFont(new Font("Tekton Pro", Font.PLAIN, 25));
		camadas.add(PassWordLabel, Integer.valueOf(1));
		
		RegisterSenhaPasswordField = new JPasswordField();
		camadas.add(RegisterSenhaPasswordField, Integer.valueOf(1));
		RegisterSenhaPasswordField.setBounds(248, 232, 202, 20);

		
		ImageIcon logo = new ImageIcon(getClass().getResource("/images/logo.png"));
		
		JButton EntrarJButton = new JButton("Cadastrar");
		EntrarJButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// action listener pra pegar as informações preenchidas nos campos
				String nomeUser = SwingCadastro.this.RegisterUserTextField.getText();
				String password = SwingCadastro.this.RegisterUserTextField.getText();
				String confirmacaopassword = String.valueOf(SwingCadastro.this.RegisterSenhaPasswordField);

				//try catch pra validar campos, e caso tenha algum erro, mostra uma janela com a exceção
				try{
					AccessControlController controller = new AccessControlController();
					controller.registerUser(nomeUser, password, confirmacaopassword);
				} catch (ControlException exception){
					JOptionPane.showMessageDialog(EntrarJButton, e);
				}

				controlador.abrirJanela("login");
			}
		});
		EntrarJButton.setBackground(new Color(124, 188, 52));
		EntrarJButton.setForeground(new Color(255, 255, 255));
		EntrarJButton.setBounds(257, 301, 89, 23);
		camadas.add(EntrarJButton, Integer.valueOf(1));
		
		JLabel ConfirmationPasswordLabel = new JLabel("Confirmação da Senha:");
		ConfirmationPasswordLabel.setBounds(10, 263, 234, 30);
		camadas.add(ConfirmationPasswordLabel, Integer.valueOf(1));
		ConfirmationPasswordLabel.setForeground(Color.WHITE);
		ConfirmationPasswordLabel.setFont(new Font("Tekton Pro", Font.PLAIN, 25));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(248, 266, 202, 20);
		camadas.add(passwordField, Integer.valueOf(1));
		JLabel logoLabel = new JLabel(logo);
		logoLabel.setBounds(190, 11, 190, 93);
		camadas.add(logoLabel, Integer.valueOf(1));
		
		JLabel LogoLabel = new JLabel("Naturagro™");
		LogoLabel.setBounds(190, 115, 234, 54);
		camadas.add(LogoLabel, Integer.valueOf(1));
		LogoLabel.setFont(new Font("Tahoma", Font.PLAIN, 44));
		LogoLabel.setForeground(new Color(255, 255, 255));
		
	}
}
