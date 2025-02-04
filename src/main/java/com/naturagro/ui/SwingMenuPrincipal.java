package com.naturagro.ui;

import java.awt.EventQueue;	

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLayeredPane;
import java.awt.Color;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JButton;

public class SwingMenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingMenuPrincipal frame = new SwingMenuPrincipal();
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
	public SwingMenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Menu Inicial");
		setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(124, 188, 52));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane camadas = new JLayeredPane();
		contentPane.add(camadas);
		camadas.setBounds(0,0,1280,720);
		
		// GridPanel Meramente pra se localizar no editor em alguns casos
		JPanel GridPanel = new JPanel();
		GridPanel.setBackground(new Color(124, 188, 52));
		GridPanel.setBounds(0, 0, 1280, 720);
		contentPane.add(GridPanel);
		GridBagLayout gbl_GridPanel = new GridBagLayout();
		gbl_GridPanel.columnWidths = new int[]{0, 0};
		gbl_GridPanel.rowHeights = new int[]{0, 0};
		gbl_GridPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_GridPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		GridPanel.setLayout(gbl_GridPanel);
		
		// Adiciona imagem de fundo
		//ImageIcon background2 = new ImageIcon(getClass().getResource("/com/naturagro/ui/images/background2edit.png"));
		//JLabel backgroundLabel = new JLabel(background2);
		//backgroundLabel.setBounds(0, 0, 1270, 681);
		//camadas.add(backgroundLabel,Integer.valueOf(0));
		
		// Adiciona logo
		//ImageIcon logo = new ImageIcon(getClass().getResource("/com/naturagro/ui/images/logo.png"));
		//JLabel logoLabel = new JLabel(logo);
		//logoLabel.setBounds(15,23,98,100);
		//camadas.add(logoLabel,Integer.valueOf(1));
		
		JLabel MenuPrincipalLabel = new JLabel("Menu Principal");
		MenuPrincipalLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		MenuPrincipalLabel.setForeground(new Color(255, 255, 255));
		MenuPrincipalLabel.setBounds(123, 46, 252, 44);
		camadas.add(MenuPrincipalLabel,Integer.valueOf(2));
		
		JButton CadastroProdutosButton = new JButton("Cadastro de Produtos");
		CadastroProdutosButton.setBackground(new Color(133, 179, 58));
		CadastroProdutosButton.setForeground(new Color(255, 255, 255));
		CadastroProdutosButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		CadastroProdutosButton.setBounds(86, 192, 482, 93);
		camadas.add(CadastroProdutosButton, Integer.valueOf(3));
		
		JButton VendasButton = new JButton("Vendas");
		VendasButton.setBackground(new Color(133,179,58));
		VendasButton.setForeground(new Color(255,255,255));
		VendasButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		VendasButton.setBounds(86, 357, 482, 93);
		camadas.add(VendasButton, Integer.valueOf(3));
		
		JButton btnNewButton_1_1 = new JButton("New button");
		btnNewButton_1_1.setBounds(86, 522, 482, 93);
		camadas.add(btnNewButton_1_1, Integer.valueOf(3));
		
		JButton ControleEstoqueButton = new JButton("Controle de Estoque");
		ControleEstoqueButton.setForeground(Color.WHITE);
		ControleEstoqueButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		ControleEstoqueButton.setBackground(new Color(133, 179, 58));
		ControleEstoqueButton.setBounds(695, 192, 482, 93);
		camadas.add(ControleEstoqueButton, Integer.valueOf(3));
		
		JButton RelatoriosButton = new JButton("Relatórios");
		RelatoriosButton.setForeground(Color.WHITE);
		RelatoriosButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		RelatoriosButton.setBackground(new Color(133, 179, 58));
		RelatoriosButton.setBounds(695, 357, 482, 93);
		camadas.add(RelatoriosButton, Integer.valueOf(3));
		
		JButton SairButton = new JButton("Sair ->|");
		SairButton.setForeground(Color.WHITE);
		SairButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		SairButton.setBackground(new Color(133, 179, 58));
		SairButton.setBounds(695, 522, 482, 93);
		camadas.add(SairButton, Integer.valueOf(3));
		
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
	}
}