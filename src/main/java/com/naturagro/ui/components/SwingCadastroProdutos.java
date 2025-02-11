
package com.naturagro.ui.components;

import com.naturagro.models.Produto;
import com.naturagro.service.ProdutoService;
import com.naturagro.ui.ControladorSwing;

import javax.persistence.TypedQuery;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.List;

public class SwingCadastroProdutos extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JLabel label;
	private JTable table;
	private ControladorSwing controlador;
	ProdutoService produtoService = new ProdutoService();

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

		// Função do botão adicionar
		JButton AdicionarButton = new JButton("Adicionar");
		AdicionarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialogCadastroProdutos dialog = new JDialogCadastroProdutos();
				dialog.setVisible(true);
			}
		});
		// Botão adicionar
		AdicionarButton.setBackground(new Color(133,179,58));
		AdicionarButton.setForeground(new Color(255,255,255));
		AdicionarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		AdicionarButton.setBounds(119, 595, 240, 50);
		camadas.add(AdicionarButton, Integer.valueOf(3));

		// Botão Editar
		JButton EditarButton = new JButton("Editar");
		EditarButton.setForeground(Color.WHITE);
		EditarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		EditarButton.setBackground(new Color(133, 179, 58));
		EditarButton.setBounds(378, 595, 240, 50);
		camadas.add(EditarButton);
		// Função do botão editar
		EditarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int coluna = table.getSelectedColumn();
				int linha = table.getSelectedRow();
				Object celula = table.getValueAt(linha,coluna);

			}
		});

		// Botão Excluir
		JButton ExcluirButton = new JButton("Excluir");
		ExcluirButton.setForeground(Color.WHITE);
		ExcluirButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		ExcluirButton.setBackground(new Color(133, 179, 58));
		ExcluirButton.setBounds(637, 595, 240, 50);
		camadas.add(ExcluirButton);
		// Função do botão excluir
		ExcluirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Pega o id do objeto selecionado
				int linha = table.getSelectedRow();
				String celula = table.getValueAt(linha,0).toString();
				long id = Long.parseLong(celula);

				ProdutoService produtoService = new ProdutoService();
				// Obtem o objeto inteiro com base no id
				Produto produto = produtoService.obterPorID(id);
				// Remove o objeto
				produtoService.remover(produto);
			}
		});

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

		// Definindo o modelo de dados da tabela
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Categoria");
		model.addColumn("Descrição");
		model.addColumn("Nome");
		model.addColumn("Preço Atacado");
		model.addColumn("Preço Varejo");

		// Armazenando a consulta do BD na variavel
		List<Object[]> consulta = produtoService.buscarPerfilProduto();

		// Definindo um ScrollPane para colocar a tabela
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(122, 125, 1014, 433);
		camadas.add(scrollPane);

		// Colocando as rows no modelo de dados
		for (Object[] linha : consulta) {
			model.addRow(linha);
		}

		table = new JTable(model);
		scrollPane.setViewportView(table);

		// Botão de recarregar
		ImageIcon reloadIcon = new ImageIcon(getClass().getResource("/images/reloadIcon50.png"));
		JButton atualizarButton = new JButton(reloadIcon);
		atualizarButton.setForeground(Color.WHITE);
		atualizarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		atualizarButton.setBackground(new Color(133, 179, 58));
		atualizarButton.setBounds(1155, 595, 50, 50);
		camadas.add(atualizarButton);
		// Função do botão recarregar
		atualizarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0); // Remove todas as linhas

				List<Object[]> consulta = produtoService.buscarPerfilProduto();

				for (Object[] linha : consulta) {
					model.addRow(linha);
				}

				table = new JTable(model);
				scrollPane.setViewportView(table);
			}
		});

		ImageIcon background2 = new ImageIcon(getClass().getResource("/images/background2edit.png"));
		JLabel backgroundLabel = new JLabel(background2);
		backgroundLabel.setBounds(0, 0, 1270, 681);
		camadas.add(backgroundLabel,Integer.valueOf(0));
	}
}
