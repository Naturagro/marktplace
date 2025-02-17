
package com.naturagro.ui.components;

import com.naturagro.models.Lote;
import com.naturagro.models.Produto;
import com.naturagro.service.LoteService;
import com.naturagro.service.ProdutoService;
import com.naturagro.ui.ControladorSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class SwingCadastroProdutos extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JLabel label;
	private JTable table;
	private ControladorSwing controlador;
	ProdutoService produtoService = new ProdutoService();
	TableModelListener model;
	private Map<Long, Produto> produtosAlteradosMap = new HashMap<>();  // Atributo da classe

	// Criando a Tela
	public SwingCadastroProdutos(ControladorSwing controladorDeTela) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Menu Inicial");
		setBounds(0, 0, 1280, 720);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(124, 188, 52));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLayeredPane camadas = new JLayeredPane();
		contentPane.add(camadas);
		camadas.setBounds(0,0,1280,720);

		ImageIcon logo = new ImageIcon(getClass().getResource("/images/logo.png"));
		JLabel logoLabel = new JLabel(logo);
		logoLabel.setBounds(15,23,98,100);
		camadas.add(logoLabel,Integer.valueOf(1));

		// Definindo o modelo de dados da tabela
		DefaultTableModel model = new DefaultTableModel() {
			// Definindo quais colunas serão editaveis
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				} if (column == 1) {
					return false;
				} else {
					return true;
				}
			}
		};

		// Adiciona ao modelo de dados as colunas que vão aparecer
		model.addColumn("Código");
		model.addColumn("Categoria");
		model.addColumn("Descrição");
		model.addColumn("Nome");
		model.addColumn("Preço");

		// Armazenando a consulta do BD na variavel
		List<Produto> consulta = produtoService.obterTodos(Integer.MAX_VALUE, 0);

		// Definindo um ScrollPane para colocar a tabela
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(122, 125, 1014, 433);
		camadas.add(scrollPane);

		// Armazenando a consulta do BD na variavel
		for (Produto linha : consulta) {
			model.addRow(new Object[]{
					linha.getId(),
					linha.getCategoria(),
					linha.getDescricao(),
					linha.getNome(),
					linha.getPreco(),
			});
		}

		// Criando tabela com o modelo e setando como visivel
		table = new JTable(model);
		scrollPane.setViewportView(table);

		// Senhor, o que eu fiz pra ter que escrever o codigo disso
		table.getModel().addTableModelListener(e -> {

			int row = e.getFirstRow(); // Detecta qual linha foi alterada
			int column = e.getColumn();
			String celula = table.getValueAt(row,0).toString();
			long id = Long.parseLong(celula); // Com base na linha alterada pega o id do produto que o usuario alterou

			// Verifica se o produto já ta no Map,se tiver, ignora
			Produto produtoOrg = produtosAlteradosMap.get(id);
			if (produtoOrg == null) {
				// Se não tiver, adiciona ao map
				produtoOrg = produtoService.obterPorID(id); // depois de pegar o id do produto q tu alterou, pega o produto do jeito que ta no banco, com base nesse id
				produtosAlteradosMap.put(id, produtoOrg); // e salva no map
			}

			// Ve qual coluna houve alteração
			if (column != -1) {
				// Pega a alteração que foi feita e armazena em "novoValor"
				String novoValor = table.getValueAt(row, column).toString();

				switch (column) {
					case 2:  // se foi coluna 2, seta a descrição do produto que antes era o produto do jeito que tava no banco, e altera
						produtoOrg.setDescricao(novoValor);
						break;
					case 3:  // Nome
						produtoOrg.setNome(novoValor);
						break;
					case 4:  // Preço
						produtoOrg.setPreco(Double.parseDouble(novoValor));
						break;
					default:
						break;
				}
			}
		});

		JLabel CadastroProdutosLabel = new JLabel("Cadastro de Produtos");
		CadastroProdutosLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		CadastroProdutosLabel.setForeground(new Color(255, 255, 255));
		CadastroProdutosLabel.setBounds(123, 46, 362, 44);
		camadas.add(CadastroProdutosLabel,Integer.valueOf(1));

		// Botão adicionar
		JButton AdicionarButton = new JButton("Adicionar");
		AdicionarButton.setBackground(new Color(83, 131, 5));
		AdicionarButton.setForeground(new Color(255,255,255));
		AdicionarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		AdicionarButton.setBounds(119, 595, 240, 50);
		camadas.add(AdicionarButton, Integer.valueOf(3));
		// Função do botão adicionar
		AdicionarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialogCadastroProdutos dialog = new JDialogCadastroProdutos();
				dialog.setVisible(true);
				atualizarTabela();
			}
		});

		// Botão Editar
		JButton EditarButton = new JButton("Editar");
		EditarButton.setForeground(Color.WHITE);
		EditarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		EditarButton.setBackground(new Color(83, 131, 5));
		EditarButton.setBounds(378, 595, 240, 50);
		camadas.add(EditarButton);
		// Função do botão editar
		EditarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// todo não sei se é de boa de abrir transação aqui, se pa isso tinha que tar no método do DAO
				produtoService.abrirT();
				for (Produto produto : produtosAlteradosMap.values()) {
					produtoService.mesclar(produto);
				}
				produtoService.fecharT();

				atualizarTabela();
			}
		});


		// Botão Excluir
		JButton ExcluirButton = new JButton("Excluir");
		ExcluirButton.setForeground(Color.WHITE);
		ExcluirButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		ExcluirButton.setBackground(new Color(83, 131, 5));
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

				atualizarTabela();
			}
		});

		// Botão Voltar
		JButton BotaoVoltar = new JButton("Voltar");
		BotaoVoltar.setForeground(Color.WHITE);
		BotaoVoltar.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		BotaoVoltar.setBackground(new Color(168, 29, 29));
		BotaoVoltar.setBounds(896, 595, 240, 50);
		camadas.add(BotaoVoltar);
		// Função do botão Voltar
		BotaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDeTela.abrirJanela("menuPrincipal");
			}
		});

		// Botão de recarregar
		ImageIcon reloadIcon = new ImageIcon(getClass().getResource("/images/reloadIcon50.png"));
		JButton atualizarButton = new JButton(reloadIcon);
		atualizarButton.setForeground(Color.WHITE);
		atualizarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		atualizarButton.setBackground(new Color(83, 131, 5));
		atualizarButton.setBounds(1155, 595, 50, 50);
		camadas.add(atualizarButton);

		// Função do botão recarregar
		atualizarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTabela();
			}
		});
		ImageIcon background2 = new ImageIcon(getClass().getResource("/images/background2edit.png"));
		JLabel backgroundLabel = new JLabel(background2);
		backgroundLabel.setBounds(0, 0, 1270, 681);
		camadas.add(backgroundLabel,Integer.valueOf(0));
	}

	private void atualizarTabela() {
		ProdutoService produtoService = new ProdutoService();

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		// Remover todos os listeners antes de atualizar a tabela
		TableModelListener[] listeners = model.getTableModelListeners();
		for (TableModelListener listener : listeners) {
			model.removeTableModelListener(listener);
		}

		// Limpar todas as linhas da tabela
		model.setRowCount(0);

		// Reconsulta o banco de dados para obter os dados atualizados
		List<Produto> consultaAtualizada = produtoService.obterTodos(Integer.MAX_VALUE, 0);

		// Preenche a tabela com os novos dados
		for (Produto linha : consultaAtualizada) {
			model.addRow(new Object[]{
					linha.getId(),
					linha.getCategoria(),
					linha.getDescricao(),
					linha.getNome(),
					linha.getPreco()
			});
		}

		// Re-adicionar os listeners
		for (TableModelListener listener : listeners) {
			model.addTableModelListener(listener);
		}

		// Notifica a tabela que os dados mudaram
		model.fireTableDataChanged();
	}
}
