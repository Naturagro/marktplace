package com.naturagro.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class SwingRelatorios extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JLabel label;

    // Criando a Tela
    public SwingRelatorios() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Menu Inicial");
		setBounds(0, 0, 1280, 720);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(124, 188, 52));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane camadas = new JLayeredPane();
		contentPane.add(camadas);
		camadas.setBounds(0,0,1280,720);
		
		ImageIcon logo = new ImageIcon(getClass().getResource("/com/naturagro/ui/images/logo.png"));
		JLabel logoLabel = new JLabel(logo);
		logoLabel.setBounds(15,23,98,100);
		camadas.add(logoLabel,Integer.valueOf(1));
		
		JLabel RelatoriosLabel = new JLabel("Relatorios");
		RelatoriosLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		RelatoriosLabel.setForeground(new Color(255, 255, 255));
		RelatoriosLabel.setBounds(123, 46, 362, 44);
		camadas.add(RelatoriosLabel,Integer.valueOf(1));
		
		JButton GerarRelatorioButton = new JButton("Gerar Relatorio");
		GerarRelatorioButton.setBackground(new Color(133,179,58));
		GerarRelatorioButton.setForeground(new Color(255,255,255));
		GerarRelatorioButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		GerarRelatorioButton.setBounds(339, 527, 268, 50);
		camadas.add(GerarRelatorioButton, Integer.valueOf(3));
		
		JButton CancelarButton = new JButton("Cancelar");
		CancelarButton.setForeground(Color.WHITE);
		CancelarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		CancelarButton.setBackground(new Color(133, 179, 58));
		CancelarButton.setBounds(633, 527, 240, 50);
		camadas.add(CancelarButton);
		
		ImageIcon background2 = new ImageIcon(getClass().getResource("/com/naturagro/ui/images/background2edit.png"));
		JLabel backgroundLabel = new JLabel(background2);
		backgroundLabel.setBounds(0, 0, 1270, 681);
		camadas.add(backgroundLabel,Integer.valueOf(0));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(133, 179, 58));
		comboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		comboBox.setForeground(new Color(255, 255, 255));
		camadas.setLayer(comboBox, 1);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Movimentação de Estoque", "Produtos Mais Vendidos"}));
		comboBox.setBounds(474, 231, 268, 50);
		camadas.add(comboBox);
		
		 try {
	            MaskFormatter dateMask = new MaskFormatter("##/##/####");
	            dateMask.setPlaceholderCharacter('_'); // Define o placeholder como "_"
	            dateMask.setAllowsInvalid(false); // Não permite caracteres inválidos
	            dateMask.setOverwriteMode(true); // Garante que os caracteres encaixem certo
		
				JFormattedTextField formattedTextField = new JFormattedTextField(dateMask);
				formattedTextField.setBackground(new Color(133, 179, 58));
				formattedTextField.setForeground(new Color(255, 255, 255));
				formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);
				formattedTextField.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
				formattedTextField.setText("__/__/__");
				camadas.setLayer(formattedTextField, 1);
				formattedTextField.setBounds(615, 394, 200, 60);
				camadas.add(formattedTextField);
				
				JFormattedTextField formattedTextField_1 = new JFormattedTextField();
				formattedTextField_1.setEditable(false);
				camadas.setLayer(formattedTextField_1, 1);
				formattedTextField_1.setText("Data:");
				formattedTextField_1.setHorizontalAlignment(SwingConstants.CENTER);
				formattedTextField_1.setForeground(Color.WHITE);
				formattedTextField_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
				formattedTextField_1.setBackground(new Color(133, 179, 58));
				formattedTextField_1.setBounds(413, 394, 200, 60);
				camadas.add(formattedTextField_1);
				
			 } catch (ParseException e) {
		            e.printStackTrace();
		        }
				
				

		
    }
}
