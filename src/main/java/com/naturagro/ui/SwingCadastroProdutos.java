package com.naturagro.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingCadastroProdutos extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JLabel label;
    private JTable table;

    // Criando a Tela
    public SwingCadastroProdutos() {
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
		
		//ImageIcon logo = new ImageIcon(getClass().getResource("/com/naturagro/ui/images/logo.png"));
		//JLabel logoLabel = new JLabel(logo);
		//logoLabel.setBounds(15,23,98,100);
		//camadas.add(logoLabel,Integer.valueOf(1));
		
		JLabel CadastroProdutosLabel = new JLabel("Cadastro de Produtos");
		CadastroProdutosLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		CadastroProdutosLabel.setForeground(new Color(255, 255, 255));
		CadastroProdutosLabel.setBounds(123, 46, 362, 44);
		camadas.add(CadastroProdutosLabel,Integer.valueOf(1));
		
		JButton SalvarButton = new JButton("Salvar");
		SalvarButton.setBackground(new Color(133,179,58));
		SalvarButton.setForeground(new Color(255,255,255));
		SalvarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		SalvarButton.setBounds(119, 595, 240, 50);
		camadas.add(SalvarButton, Integer.valueOf(3));
		
		JButton EditarButton = new JButton("Editar");
		EditarButton.setForeground(Color.WHITE);
		EditarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		EditarButton.setBackground(new Color(133, 179, 58));
		EditarButton.setBounds(378, 595, 240, 50);
		camadas.add(EditarButton);
		
		JButton ExcluirButton = new JButton("Excluir");
		ExcluirButton.setForeground(Color.WHITE);
		ExcluirButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		ExcluirButton.setBackground(new Color(133, 179, 58));
		ExcluirButton.setBounds(637, 595, 240, 50);
		camadas.add(ExcluirButton);
		
		JButton CancelarButton = new JButton("Cancelar");
		CancelarButton.setForeground(Color.WHITE);
		CancelarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		CancelarButton.setBackground(new Color(133, 179, 58));
		CancelarButton.setBounds(896, 595, 240, 50);
		camadas.add(CancelarButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(122, 125, 1014, 433);
		camadas.add(scrollPane);
		
		//ImageIcon background2 = new ImageIcon(getClass().getResource("/com/naturagro/ui/images/background2edit.png"));
		//JLabel backgroundLabel = new JLabel(background2);
		//backgroundLabel.setBounds(0, 0, 1270, 681);
		//camadas.add(backgroundLabel,Integer.valueOf(0));
		
		table = new JTable();
		scrollPane.setViewportView(table);

		
    }
}
