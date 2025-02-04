package com.naturagro.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingVendas extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JLabel label;
    private JTable table;

    // Criando a Tela
    public SwingVendas() {
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
		
		JLabel VendasLabel = new JLabel("Vendas");
		VendasLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		VendasLabel.setForeground(new Color(255, 255, 255));
		VendasLabel.setBounds(123, 46, 362, 44);
		camadas.add(VendasLabel,Integer.valueOf(1));
		
		JButton AdicionarButton = new JButton("Adicionar");
		AdicionarButton.setBackground(new Color(133,179,58));
		AdicionarButton.setForeground(new Color(255,255,255));
		AdicionarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		AdicionarButton.setBounds(119, 595, 240, 50);
		camadas.add(AdicionarButton, Integer.valueOf(3));
		
		JButton RemoverButton = new JButton("Remover");
		RemoverButton.setForeground(Color.WHITE);
		RemoverButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		RemoverButton.setBackground(new Color(133, 179, 58));
		RemoverButton.setBounds(378, 595, 240, 50);
		camadas.add(RemoverButton);
		
		JButton FinalizarButton = new JButton("Finalizar");
		FinalizarButton.setForeground(Color.WHITE);
		FinalizarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		FinalizarButton.setBackground(new Color(133, 179, 58));
		FinalizarButton.setBounds(637, 595, 240, 50);
		camadas.add(FinalizarButton);
		
		JButton CancelarButton = new JButton("Cancelar");
		CancelarButton.setForeground(Color.WHITE);
		CancelarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		CancelarButton.setBackground(new Color(133, 179, 58));
		CancelarButton.setBounds(896, 595, 240, 50);
		camadas.add(CancelarButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(122, 125, 1014, 433);
		camadas.add(scrollPane);
		
		ImageIcon background2 = new ImageIcon(getClass().getResource("/com/naturagro/ui/images/background2edit.png"));
		JLabel backgroundLabel = new JLabel(background2);
		backgroundLabel.setBounds(0, 0, 1270, 681);
		camadas.add(backgroundLabel,Integer.valueOf(0));
		
		table = new JTable();
		scrollPane.setViewportView(table);

		
    }
}
