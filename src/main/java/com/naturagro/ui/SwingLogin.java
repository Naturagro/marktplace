package com.naturagro.ui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Font;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SwingLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField LoginUserTextField;
	private JPasswordField LoginSenhaPasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingCadastro frame = new SwingCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SwingLogin() {
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
		LoginUserTextField = new JTextField();
		LoginUserTextField.setBounds(248, 199, 202, 20);
		camadas.add(LoginUserTextField, Integer.valueOf(1));
		
		JLabel PassWordLabel = new JLabel("Senha:");
		PassWordLabel.setSize(70, 30);
		PassWordLabel.setLocation(169, 230);
		PassWordLabel.setForeground(Color.WHITE);
		PassWordLabel.setFont(new Font("Tekton Pro", Font.PLAIN, 25));
		camadas.add(PassWordLabel, Integer.valueOf(1));
		
		LoginSenhaPasswordField = new JPasswordField();
		camadas.add(LoginSenhaPasswordField, Integer.valueOf(1));
		LoginSenhaPasswordField.setBounds(248, 232, 202, 20);

		
		ImageIcon logo = new ImageIcon(getClass().getResource("/images/logo.png"));
		
		JButton LoginJButton = new JButton("Entrar");
		LoginJButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		LoginJButton.setBackground(new Color(124, 188, 52));
		LoginJButton.setForeground(new Color(255, 255, 255));
		LoginJButton.setBounds(257, 301, 89, 23);
		camadas.add(LoginJButton, Integer.valueOf(1));
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
