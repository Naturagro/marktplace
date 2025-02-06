package com.naturagro.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SwingCadastroProdutos extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JLabel label;
	private JTable table;
	private ControladorSwing controlador;


	// Criando a Tela
	public SwingCadastroProdutos(ControladorSwing controladorDeTela) {
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

		ImageIcon logo = new ImageIcon(getClass().getResource("/images/logo.png"));
		JLabel logoLabel = new JLabel(logo);
		logoLabel.setBounds(15,23,98,100);
		camadas.add(logoLabel,Integer.valueOf(1));

		JLabel CadastroProdutosLabel = new JLabel("Cadastro de Produtos");
		CadastroProdutosLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		CadastroProdutosLabel.setForeground(new Color(255, 255, 255));
		CadastroProdutosLabel.setBounds(123, 46, 362, 44);
		camadas.add(CadastroProdutosLabel,Integer.valueOf(1));

		JButton AdicionarButton = new JButton("Adicionar");
		AdicionarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Pra chamar a tela do botão adicionar precisa definir uma hashmap "campos" com esses tipos para key e value
				Map<JTextField, String> campos = new HashMap<>();
				// Instanciar todos os textfields que a tela vai ter em uma variavel
				JTextField nomeProduto = new JTextField();
				JTextField preco = new JTextField();
				JTextField codigo = new JTextField();
				JTextField validade = new JTextField();

				// depois é só preencher o Map com o objeto txtfield criado e a string das labels a esquerda deles
				campos.put(nomeProduto, "Nome do Produto:");
				campos.put(preco, "Preço:");
				campos.put(codigo, "Código:");
				campos.put(validade, "Validade do produto:");

				// Agora, cria o JDialog e exibe ele
				SwingAdicionar dialog = new SwingAdicionar(campos);
				dialog.setVisible(true); // Exibe o JDialog

				// OBS: Pra pegar as informações dos textFields continua igual EX: nomeProduto.getText()
			}
		});
		AdicionarButton.setBackground(new Color(133,179,58));
		AdicionarButton.setForeground(new Color(255,255,255));
		AdicionarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		AdicionarButton.setBounds(119, 595, 240, 50);
		camadas.add(AdicionarButton, Integer.valueOf(3));

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

		// Botão Voltar
		JButton BotaoVoltar = new JButton("Voltar");
		BotaoVoltar.setForeground(Color.WHITE);
		BotaoVoltar.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		BotaoVoltar.setBackground(new Color(133, 179, 58));
		BotaoVoltar.setBounds(896, 595, 240, 50);
		camadas.add(BotaoVoltar);
		// Função do botão Voltar
		BotaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDeTela.abrirJanela("menuPrincipal");
			}
		});


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(122, 125, 1014, 433);
		camadas.add(scrollPane);

		ImageIcon background2 = new ImageIcon(getClass().getResource("/images/background2edit.png"));
		JLabel backgroundLabel = new JLabel(background2);
		backgroundLabel.setBounds(0, 0, 1270, 681);
		camadas.add(backgroundLabel,Integer.valueOf(0));

		table = new JTable();
		scrollPane.setViewportView(table);


	}
}
