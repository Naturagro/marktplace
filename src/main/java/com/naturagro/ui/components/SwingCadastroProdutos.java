package com.naturagro.ui.components;

import com.naturagro.models.Produto;
import com.naturagro.service.ProdutoService;
import com.naturagro.ui.ControladorSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingCadastroProdutos extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private ControladorSwing controlador;
	private ProdutoService produtoService = new ProdutoService();
	private Map<Long, Produto> produtosAlteradosMap = new HashMap<>();

	public SwingCadastroProdutos(ControladorSwing controladorDeTela) {
		this.controlador = controladorDeTela;
		setTitle("Cadastro de Produtos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setLocationRelativeTo(null);

		// Painel principal com borda aumentada
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(new Color(124, 188, 52));
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(mainPanel);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		// Imagem de fundo
		ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/images/background2edit.png"));
		JLabel backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		mainPanel.add(backgroundLabel, gbc);

		// Painel sobreposto para os componentes
		JPanel overlayPanel = new JPanel(new GridBagLayout());
		overlayPanel.setOpaque(false);
		GridBagConstraints overlayGbc = new GridBagConstraints();
		overlayGbc.insets = new Insets(10, 10, 10, 10);
		overlayGbc.fill = GridBagConstraints.BOTH;
		overlayGbc.weightx = 1.0;
		overlayGbc.weighty = 1.0;
		overlayGbc.gridx = 0;
		overlayGbc.gridy = 0;
		backgroundLabel.add(overlayPanel, overlayGbc);

		// Título alinhado à esquerda
		overlayGbc = new GridBagConstraints();
		overlayGbc.insets = new Insets(10, 10, 10, 10);
		overlayGbc.fill = GridBagConstraints.BOTH;
		overlayGbc.gridx = 0;
		overlayGbc.gridy = 0;
		overlayGbc.gridwidth = 4;
		JLabel titleLabel = new JLabel("Cadastro de Produtos");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		overlayPanel.add(titleLabel, overlayGbc);

		// Criação dos botões com a mesma formatação
		JButton adicionarButton = criarBotao("Adicionar", e -> {
			new JDialogCadastroProdutos().setVisible(true);
			atualizarTabela();
		});
		JButton editarButton = criarBotao("Editar", e -> salvarAlteracoes());
		JButton excluirButton = criarBotao("Excluir", e -> excluirProduto());
		JButton voltarButton = criarBotao("Voltar", e -> controladorDeTela.abrirJanela("menuPrincipal"));
		// Apenas alterando a cor do botão "Voltar"
		voltarButton.setBackground(new Color(163, 43, 43));

		// Linha de botões: posicionada mais abaixo do título com expansão
		overlayGbc = new GridBagConstraints();
		overlayGbc.insets = new Insets(20, 10, 10, 10); // espaço superior aumentado
		overlayGbc.fill = GridBagConstraints.BOTH;
		overlayGbc.gridy = 1;
		overlayGbc.gridwidth = 1;
		overlayGbc.weightx = 1.0;
		overlayGbc.weighty = 0.1;
		overlayGbc.ipady = 30; // aumenta a altura dos botões

		overlayGbc.gridx = 0;
		overlayPanel.add(adicionarButton, overlayGbc);
		overlayGbc.gridx = 1;
		overlayPanel.add(editarButton, overlayGbc);
		overlayGbc.gridx = 2;
		overlayPanel.add(excluirButton, overlayGbc);
		overlayGbc.gridx = 3;
		overlayPanel.add(voltarButton, overlayGbc);

		// Tabela de produtos
		DefaultTableModel model = new DefaultTableModel(new Object[]{"Código", "Categoria", "Descrição", "Nome", "Preço"}, 0);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
		JScrollPane scrollPane = new JScrollPane(table);

		overlayGbc = new GridBagConstraints();
		overlayGbc.insets = new Insets(10, 10, 10, 10);
		overlayGbc.fill = GridBagConstraints.BOTH;
		overlayGbc.gridx = 0;
		overlayGbc.gridy = 2;
		overlayGbc.gridwidth = 4;
		overlayGbc.weightx = 1.0;
		overlayGbc.weighty = 1.0;
		overlayPanel.add(scrollPane, overlayGbc);

		List<Produto> consulta = produtoService.obterTodos(Integer.MAX_VALUE, 0);
		for (Produto linha : consulta) {
			model.addRow(new Object[]{linha.getId(), linha.getCategoria(), linha.getDescricao(), linha.getNome(), linha.getPreco()});
		}

		table.getModel().addTableModelListener(e -> {
			int row = e.getFirstRow();
			int column = e.getColumn();
			if (row < 0 || row >= table.getRowCount()) {
				return;
			}
			long id = Long.parseLong(table.getValueAt(row, 0).toString());
			Produto produtoOrg = produtosAlteradosMap.getOrDefault(id, produtoService.obterPorID(id));
			produtosAlteradosMap.put(id, produtoOrg);
			if (column != -1) {
				String novoValor = table.getValueAt(row, column).toString();
				switch (column) {
					case 2 -> produtoOrg.setDescricao(novoValor);
					case 3 -> produtoOrg.setNome(novoValor);
					case 4 -> produtoOrg.setPreco(Double.parseDouble(novoValor));
				}
			}
		});

		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				Image img = backgroundIcon.getImage();
				Image newImg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
				backgroundLabel.setIcon(new ImageIcon(newImg));
			}
		});
	}

	private JButton criarBotao(String texto, ActionListener action) {
		JButton botao = new JButton(texto);
		botao.setFont(new Font("SansSerif", Font.PLAIN, 30));
		botao.setBackground(new Color(83, 131, 5));
		botao.setForeground(Color.WHITE);
		botao.addActionListener(action);
		botao.setPreferredSize(new Dimension(200, 50));
		botao.setMinimumSize(new Dimension(150, 40));
		botao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		return botao;
	}

	private void salvarAlteracoes() {
		produtoService.abrirT();
		try {
			for (Produto produto : produtosAlteradosMap.values()) {
				produtoService.mesclar(produto);
			}
			produtoService.fecharT();
			JOptionPane.showMessageDialog(null, "Produtos editados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			atualizarTabela();
		} catch (Exception ex) {
			produtoService.fecharT();
			JOptionPane.showMessageDialog(null, "Erro ao editar produtos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void excluirProduto() {
		int linha = table.getSelectedRow();
		if (linha != -1) {
			long id = Long.parseLong(table.getValueAt(linha, 0).toString());
			Produto produto = produtoService.obterPorID(id);
			produtoService.remover(produto);
			atualizarTabela();
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um produto para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void atualizarTabela() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		List<Produto> consultaAtualizada = produtoService.obterTodos(Integer.MAX_VALUE, 0);
		for (Produto linha : consultaAtualizada) {
			model.addRow(new Object[]{linha.getId(), linha.getCategoria(), linha.getDescricao(), linha.getNome(), linha.getPreco()});
		}
		model.fireTableDataChanged();
	}
}
