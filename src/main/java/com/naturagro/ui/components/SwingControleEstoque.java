package com.naturagro.ui.components;

import com.naturagro.models.Lote;
import com.naturagro.models.Produto;
import com.naturagro.service.LoteService;
import com.naturagro.service.ProdutoService;
import com.naturagro.ui.ControladorSwing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SwingControleEstoque extends JFrame {
	private static final long serialVersionUID = 1L;
	private ControladorSwing controlador;
	private JLabel backgroundLabel;
	private JTable estoqueTable;

	// Tela
	public SwingControleEstoque(ControladorSwing controladorDeTela) {
		ProdutoService produtoService = new ProdutoService();
		LoteService loteService = new LoteService();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Controle de Estoque");
		setBounds(0, 0, 1280, 720);

		// Painel principal com bordas verdes e fundo transparente
		JPanel PainelPrincipal = new JPanel(new GridBagLayout());
		PainelPrincipal.setBackground(new Color(124, 188, 52)); // Cor verde
		PainelPrincipal.setOpaque(true); // Garantir que o fundo seja preenchido com verde
		setContentPane(PainelPrincipal);

		// Configuração do layout do painel de conteúdo
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		// Adicionando imagem de fundo
		ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/images/background2edit.png"));
		backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 4;
		PainelPrincipal.add(backgroundLabel, gbc);

		// Painel de componentes (sobrepondo a imagem de fundo)
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);  // Tornar o painel transparente para ver a imagem de fundo
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		backgroundLabel.add(panel, gbc);

		GridBagConstraints innerGbc = new GridBagConstraints();
		innerGbc.insets = new Insets(10, 10, 10, 10);
		innerGbc.fill = GridBagConstraints.BOTH;
		innerGbc.weightx = 1.0;
		innerGbc.weighty = 1.0;

		// Título "Controle de Estoque"
		JLabel ControleEstoqueLabel = new JLabel("Controle de Estoque");
		ControleEstoqueLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		ControleEstoqueLabel.setForeground(new Color(255, 255, 255)); // Cor branca para contraste
		innerGbc.gridx = 0;
		innerGbc.gridy = 0;
		innerGbc.gridwidth = 4;
		panel.add(ControleEstoqueLabel, innerGbc);

		// Definindo o modelo de dados da tabela
		DefaultTableModel model = new DefaultTableModel() {
			// Definindo quais colunas serão editaveis
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Adiciona ao modelo de dados as colunas que vão aparecer
		model.addColumn("ID");
		model.addColumn("Data de Entrada");
		model.addColumn("Data de Vencimento");
		model.addColumn("Quantidade em Estoque:");
		model.addColumn("Codigo de Barras:");
		model.addColumn("Nome do Produto:");

		// Armazenando a consulta do BD na variavel
		List<Produto> consulta = produtoService.obterTodos();

		List<Lote> consultaLote = loteService.obterTodos(Integer.MAX_VALUE, 0);

		// Armazenando a consulta do BD na variavel
		for (Lote linha : consultaLote) {
			model.addRow(new Object[]{
					linha.getId(),
					linha.getDataEntrada(),
					linha.getDataVencimento(),
					linha.getQuantidade(),
					linha.getProduto().getId(),
					linha.getProduto().getNome()
			});
		}

		// Tabela
		estoqueTable = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(estoqueTable);

		// Colocando a tabela dentro de um JScrollPane
		innerGbc.gridx = 0;
		innerGbc.gridy = 2;
		innerGbc.gridwidth = 4;
		innerGbc.fill = GridBagConstraints.BOTH;
		innerGbc.weightx = 1;
		innerGbc.weighty = 1;
		panel.add(scrollPane, innerGbc);

		// Botões
		JButton AdicionarButton = new JButton("Adicionar");
		AdicionarButton.setBackground(new Color(83, 131, 5));
		AdicionarButton.setForeground(new Color(255, 255, 255));
		AdicionarButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
		AdicionarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialogControleEstoque dialog = new JDialogControleEstoque();
				dialog.setVisible(true);

				atualizarTabela();
			}
		});

		JButton RemoverButton = new JButton("Remover");
		RemoverButton.setForeground(Color.WHITE);
		RemoverButton.setFont(new Font("SansSerif", Font.PLAIN, 30));
		RemoverButton.setBackground(new Color(83, 131, 5));
		RemoverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Pega o id do objeto selecionado
				int linha = estoqueTable.getSelectedRow();
				String celula = estoqueTable.getValueAt(linha,0).toString();
				long id = Long.parseLong(celula);

				LoteService loteService = new LoteService();
				// Obtem o objeto inteiro com base no id
				Lote lote = loteService.obterPorID(id);
				// Remove o objeto
				loteService.remover(lote);

				atualizarTabela();

			}
		});

		JButton BotaoVoltar = new JButton("Voltar");
		BotaoVoltar.setForeground(Color.WHITE);
		BotaoVoltar.setFont(new Font("SansSerif", Font.PLAIN, 30));
		BotaoVoltar.setBackground(new Color(163, 43, 43)); // Cor alterada
		// Função do botão Voltar
		BotaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDeTela.abrirJanela("menuPrincipal");
			}
		});

		// Colocando os botões
		innerGbc.gridwidth = 1;
		innerGbc.gridx = 0;
		innerGbc.gridy = 1;
		panel.add(AdicionarButton, innerGbc);

		innerGbc.gridx = 1;
		panel.add(RemoverButton, innerGbc);

		innerGbc.gridx = 2;
		innerGbc.weightx = 1;
		panel.add(BotaoVoltar, innerGbc);

		// Centralizando a tela
		setLocationRelativeTo(null);
	}

	private void atualizarTabela() {
		LoteService loteService = new LoteService();

		DefaultTableModel model = (DefaultTableModel) estoqueTable.getModel();

		// Limpa todas as linhas da tabela
		model.setRowCount(0);

		// Reconsulta o banco de dados para obter os dados atualizados
		List<Lote> consultaAtualizada = loteService.obterTodos(Integer.MAX_VALUE, 0);

		// Preenche a tabela com os novos dados
		for (Lote linha : consultaAtualizada) {
			model.addRow(new Object[]{
					linha.getId(),
					linha.getDataEntrada(),
					linha.getDataVencimento(),
					linha.getQuantidade(),
					linha.getProduto() != null ? linha.getProduto().getId() : "Sem produto",
					linha.getProduto() != null ? linha.getProduto().getNome() : "Sem produto"
			});
		}

		// Notifica a tabela que os dados mudaram
		model.fireTableDataChanged();
	}
}
