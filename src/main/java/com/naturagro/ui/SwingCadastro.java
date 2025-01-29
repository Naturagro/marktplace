package com.naturagro.ui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
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

public class SwingCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField RegisterUserTextField;
	private JPasswordField RegisterSenhaPasswordField;

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
	public SwingCadastro() {
		setBackground(new Color(112, 140, 52));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 390);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 140, 52));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LogoLabel = new JLabel("Naturagro™");
		LogoLabel.setBounds(195, 126, 234, 54);
		LogoLabel.setFont(new Font("Tahoma", Font.PLAIN, 44));
		LogoLabel.setForeground(new Color(255, 255, 255));
		contentPane.add(LogoLabel);
		
		JPanel RegisterInfoPanel = new JPanel();
		RegisterInfoPanel.setBackground(new Color(112, 140, 52));
		RegisterInfoPanel.setBounds(159, 191, 291, 75);
		contentPane.add(RegisterInfoPanel);
		RegisterInfoPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel UserLabel = new JLabel("Usuário:");
		UserLabel.setForeground(new Color(255, 255, 255));
		UserLabel.setFont(new Font("Tekton Pro", Font.PLAIN, 25));
		RegisterInfoPanel.add(UserLabel, "2, 2");
		

		// Nota: Se eu tiver MUITA coragem, eu crio uma subclasse de JTextField e faço uma classe onde o text field tem border radius
		RegisterUserTextField = new JTextField();
		RegisterInfoPanel.add(RegisterUserTextField, "4, 2, 5, 1, fill, default");
		RegisterUserTextField.setColumns(10);
		
		JLabel PassWordLabel = new JLabel("Senha:");
		PassWordLabel.setForeground(Color.WHITE);
		PassWordLabel.setFont(new Font("Tekton Pro", Font.PLAIN, 25));
		RegisterInfoPanel.add(PassWordLabel, "2, 4");
		
		RegisterSenhaPasswordField = new JPasswordField();
		RegisterInfoPanel.add(RegisterSenhaPasswordField, "3, 4, 6, 1, fill, default");
		
		ImageIcon logo = new ImageIcon(getClass().getResource("/com/naturagro/ui/images/logo.png"));
		JLabel logoLabel = new JLabel(logo);
		logoLabel.setBounds(195, 22, 190, 93);
		contentPane.add(logoLabel);
		
		JButton EntrarJButton = new JButton("Cadastrar");
		EntrarJButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		EntrarJButton.setBackground(new Color(124, 188, 52));
		EntrarJButton.setForeground(new Color(255, 255, 255));
		EntrarJButton.setBounds(257, 301, 89, 23);
		contentPane.add(EntrarJButton);
		
		JLabel ConfirmationPasswordLabel = new JLabel("Confirmação da Senha");
		ConfirmationPasswordLabel.setBounds(10, 263, 227, 30);
		contentPane.add(ConfirmationPasswordLabel);
		ConfirmationPasswordLabel.setForeground(Color.WHITE);
		ConfirmationPasswordLabel.setFont(new Font("Tekton Pro", Font.PLAIN, 25));
		
	}
}
