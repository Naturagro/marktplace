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

		// Cria o painel principal com GridBagLayout
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(new Color(124, 188, 52));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		// Adiciona o label que contém a imagem de fundo (usando a mesma imagem do SwingVendas)
		ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/images/background2edit.png"));
		JLabel backgroundLabel = new JLabel(backgroundIcon);
		backgroundLabel.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		mainPanel.add(backgroundLabel, gbc);

		// Painel sobreposto (transparente) para adicionar os componentes
		JPanel overlayPanel = new JPanel(new GridBagLayout());
		overlayPanel.setOpaque(false); // Permite visualizar o fundo
		GridBagConstraints overlayGbc = new GridBagConstraints();
		overlayGbc.insets = new Insets(5, 5, 5, 5);
		overlayGbc.fill = GridBagConstraints.BOTH;
		overlayGbc.weightx = 1.0;
		overlayGbc.weighty = 1.0;
		overlayGbc.gridx = 0;
		overlayGbc.gridy = 0;
		backgroundLabel.add(overlayPanel, overlayGbc);

		// --- Componentes adicionados no overlayPanel ---
		// Logo
		overlayGbc = new GridBagConstraints();
		overlayGbc.insets = new Insets(5, 5, 5, 5);
		overlayGbc.fill = GridBagConstraints.BOTH;
		overlayGbc.gridx = 0;
		overlayGbc.gridy = 0;
		overlayGbc.gridwidth = 1;
		JLabel logoLabel = new JLabel(new ImageIcon(getClass().getResource("/images/logo.png")));
		overlayPanel.add(logoLabel, overlayGbc);

		// Título
		overlayGbc = new GridBagConstraints();
		overlayGbc.insets = new Insets(5, 5, 5, 5);
		overlayGbc.fill = GridBagConstraints.BOTH;
		overlayGbc.gridx = 1;
		overlayGbc.gridy = 0;
		overlayGbc.gridwidth = 2;
		JLabel titleLabel = new JLabel("Cadastro de Produtos");
		titleLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		titleLabel.setForeground(Color.WHITE);
		overlayPanel.add(titleLabel, overlayGbc);

		// Tabela de produtos
		DefaultTableModel model = new DefaultTableModel(new Object[]{"Código", "Categoria", "Descrição", "Nome", "Preço"}, 0);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(1000, 400));
		JScrollPane scrollPane = new JScrollPane(table);
		overlayGbc = new GridBagConstraints();
		overlayGbc.insets = new Insets(5, 5, 5, 5);
		overlayGbc.fill = GridBagConstraints.BOTH;
		overlayGbc.gridx = 0;
		overlayGbc.gridy = 1;
		overlayGbc.gridwidth = 3;
		overlayGbc.weightx = 1.0;
		overlayGbc.weighty = 1.0;
		overlayPanel.add(scrollPane, overlayGbc);

		// Preenche a tabela com os dados dos produtos
		List<Produto> consulta = produtoService.obterTodos(Integer.MAX_VALUE, 0);
		for (Produto linha : consulta) {
			model.addRow(new Object[]{linha.getId(), linha.getCategoria(), linha.getDescricao(), linha.getNome(), linha.getPreco()});
		}

		// Listener para alterações na tabela (para atualizar os produtos modificados)
		table.getModel().addTableModelListener(e -> {
			int row = e.getFirstRow();
			int column = e.getColumn();
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

		// Criação dos botões mantendo as mesmas posições originais
		JButton adicionarButton = criarBotao("Adicionar", e -> {
			new JDialogCadastroProdutos().setVisible(true);
			atualizarTabela();
		});
		JButton editarButton = criarBotao("Editar", e -> salvarAlteracoes());
		JButton excluirButton = criarBotao("Excluir", e -> excluirProduto());
		JButton voltarButton = criarBotao("Voltar", e -> controladorDeTela.abrirJanela("menuPrincipal"));

		// Botão Atualizar com mesmo estilo dos demais botões
		JButton atualizarButton = new JButton(new ImageIcon(getClass().getResource("/images/reloadIcon50.png")));
		atualizarButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		atualizarButton.setBackground(new Color(83, 131, 5));
		atualizarButton.setForeground(Color.WHITE);
		atualizarButton.addActionListener(e -> atualizarTabela());

		// Linha de botões: configurando weightx = 1.0 para igualar o tamanho
		overlayGbc = new GridBagConstraints();
		overlayGbc.insets = new Insets(5, 5, 5, 5);
		overlayGbc.fill = GridBagConstraints.BOTH;
		overlayGbc.gridy = 2;
		overlayGbc.gridwidth = 1;
		overlayGbc.weightx = 1.0;

		overlayGbc.gridx = 0;
		overlayPanel.add(adicionarButton, overlayGbc);

		overlayGbc.gridx = 1;
		overlayPanel.add(editarButton, overlayGbc);

		overlayGbc.gridx = 2;
		overlayPanel.add(excluirButton, overlayGbc);

		overlayGbc.gridx = 3;
		overlayPanel.add(voltarButton, overlayGbc);

		overlayGbc.gridx = 4;
		overlayPanel.add(atualizarButton, overlayGbc);

		// Redimensiona a imagem de fundo quando a janela é redimensionada
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
		botao.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		botao.setBackground(new Color(83, 131, 5));
		botao.setForeground(Color.WHITE);
		botao.addActionListener(action);
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
