package com.naturagro.ui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.naturagro.models.Funcionario;
import com.naturagro.models.Produto;
import com.naturagro.models.Venda;
import com.naturagro.service.ProdutoService;
import com.naturagro.service.VendaService;
import com.naturagro.ui.ControladorSwing;
import com.naturagro.ui.ProdutoSelecionadoListener;

public class SwingVendas extends JFrame {
	private static final long serialVersionUID = 1L;
	private ControladorSwing controlador;
	private JLabel backgroundLabel;
	private JTable table;
	private DefaultTableModel model;

	public SwingVendas(ControladorSwing controladorDeTela) {
		ProdutoService produtoService = new ProdutoService();
		VendaService vendaService = new VendaService();
		this.controlador = controladorDeTela;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Menu Vendas");
		setBounds(0, 0, 1280, 720);

		// Painel principal com layout GridBagLayout e fundo verde
		JPanel PainelPrincipal = new JPanel(new GridBagLayout());
		PainelPrincipal.setBackground(new Color(124, 188, 52)); // Cor verde para a borda
		PainelPrincipal.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));  // Nenhuma borda adicional
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

		// Label do "Total a Pagar"
		JLabel totalPagarLabel = new JLabel("Total a Pagar:");
		totalPagarLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		totalPagarLabel.setForeground(new Color(255, 255, 255));  // Cor branca para contraste
		innerGbc.gridx = 0;
		innerGbc.gridy = 3;
		innerGbc.gridwidth = 4;
		panel.add(totalPagarLabel, innerGbc);

		// Label do valor total em reais
		JLabel valorTotalLabel = new JLabel("R$ 00.00");
		valorTotalLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		valorTotalLabel.setForeground(new Color(255, 255, 255));  // Cor branca para contraste
		innerGbc.gridx = 3;
		innerGbc.gridy = 3;
		innerGbc.anchor = GridBagConstraints.EAST;
		innerGbc.gridwidth = 4;
		panel.add(valorTotalLabel, innerGbc);

		// Tabela
		// Definindo o modelo de dados da tabela
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Bloqueia a edição da linha "Total a Pagar"
				if (row == getRowCount() - 1) {
					return false;
				}
				return false;
			}
		};

		// Adiciona ao modelo de dados as colunas que vão aparecer
		model.addColumn("Código");
		model.addColumn("Produto");
		model.addColumn("Preço em R$ P/Unidade");
		model.addColumn("Quantidade");
		model.addColumn("Total");

		// Criando a tabela
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);

		// Botões

		// Botão Adicionar
		JButton BotaoAdcionar = new JButton("Adicionar");
		BotaoAdcionar.setBackground(new Color(83, 131, 5));
		BotaoAdcionar.setForeground(new Color(255, 255, 255));
		BotaoAdcionar.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		BotaoAdcionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialogVendas dialog = new JDialogVendas(new ProdutoSelecionadoListener() {
					@Override
					public void onProdutoSelecionado(Produto produto,Integer quantidade) {

						Double totalAcumulado = 0.0;

						// Adiciona o produto e a quantidade pego pela interface no JDialog na tabela
						model.addRow(new Object[]{
								produto.getId(),
								produto.getNome(),
								String.format("R$ %.2f", produto.getPreco()),
								quantidade,
								String.format("R$ %.2f", produto.getPreco() * quantidade)

						});

						// Calcular total acumulado
						for (int i = 0; i < model.getRowCount(); i++) {
							String valorStr = model.getValueAt(i, 4).toString().replace("R$", "").trim().replace(",", ".");
							totalAcumulado += Double.parseDouble(valorStr);
						}

						String totalAcumuladoStr = totalAcumulado.toString();
						valorTotalLabel.setText("R$ "+totalAcumuladoStr);

					}
				});
				dialog.setVisible(true);
			}
		});

		// Botão Finalizar
		JButton BotaoFinalizar = new JButton("Finalizar");
		BotaoFinalizar.setBackground(new Color(83, 131, 5));
		BotaoFinalizar.setForeground(new Color(255, 255, 255));
		BotaoFinalizar.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		BotaoFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				Double totalAcumulado = 0.0;
				List<Produto> produtos = new ArrayList<>();
				Funcionario teste = new Funcionario();// todo funcionario teste, só até ter o metodo de pegar funcionario logado

				// Cria Lista de produtos a serem vendidos (para a venda), e da Baixa no estoque
				for (int i = 0; i < model.getRowCount(); i++) {

					Long id = (Long) table.getValueAt(i,0);
					Integer quantidadeVendida = (int) table.getValueAt(i,3);

					Produto produto = produtoService.obterPorID(id);
					produtos.add(produto);

					// todo acredito que isso devia sair daqui e ser feito automatico na parte de vendas
					//produtoService.atualizarEstoque(id,quantidadeVendida);
				}

				// Cria a venda e salva no BD
				Venda venda = new Venda(teste,produtos);
				vendaService.salvarVenda(venda);

				model.setRowCount(0); // Remove todas as linhas

				// Calcular total acumulado
				for (int i = 0; i < model.getRowCount(); i++) {
					String valorStr = model.getValueAt(i, 4).toString().replace("R$", "").trim().replace(",", ".");
					totalAcumulado += Double.parseDouble(valorStr);
				}
				String totalAcumuladoStr = totalAcumulado.toString();
				valorTotalLabel.setText("R$ "+totalAcumuladoStr);

			}
		});

		// Botão Voltar
		JButton BotaoVoltar = new JButton("Voltar");
		BotaoVoltar.setBackground(new Color(168, 29, 29));
		BotaoVoltar.setForeground(new Color(255, 255, 255));
		BotaoVoltar.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		// Função do botão voltar
		BotaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDeTela.abrirJanela("menuPrincipal");
			}
		});

		// Botão Remover
		JButton BotaoRemover = new JButton("Remover");
		BotaoRemover.setBackground(new Color(83, 131, 5));
		BotaoRemover.setForeground(new Color(255, 255, 255));
		BotaoRemover.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		BotaoRemover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int linhaSelecionada = table.getSelectedRow();
				if (linhaSelecionada != -1) { // Verifica se alguma linha está selecionada
					model.removeRow(linhaSelecionada); // Remove a linha selecionada do modelo da tabela
				}

				int colunaPreço = 4;
				Double totalAcumulado = 0.0;

				// Calcular total acumulado
				for (int i = 0; i < model.getRowCount(); i++) {
					String valorStr = model.getValueAt(i, 4).toString().replace("R$", "").trim().replace(",", ".");
					totalAcumulado += Double.parseDouble(valorStr);
				}

				String totalAcumuladoStr = totalAcumulado.toString();
				valorTotalLabel.setText("R$ "+totalAcumuladoStr);

			}
		});

		// Botões (com exceção do voltar)
		innerGbc.gridwidth = 1;
		innerGbc.gridx = 0;
		innerGbc.gridy = 0;
		panel.add(BotaoAdcionar, innerGbc);

		innerGbc.gridx = 1;
		panel.add(BotaoRemover, innerGbc);

		innerGbc.gridx = 2;
		panel.add(BotaoFinalizar, innerGbc);

		// Botão Voltar
		innerGbc.gridx = 3;
		innerGbc.gridy = 0;
		panel.add(BotaoVoltar, innerGbc);

		// Colocando tabela dentro de um JScrollPane
		innerGbc.gridx = 0;
		innerGbc.gridy = 2;
		innerGbc.gridwidth = 4;
		innerGbc.fill = GridBagConstraints.BOTH;
		innerGbc.weightx = 1;
		innerGbc.weighty = 1;
		panel.add(scrollPane, innerGbc);

		// Listener para redimensionar a imagem de fundo quando a janela for redimensionada
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				Image img = backgroundIcon.getImage();
				Image newImg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
				backgroundLabel.setIcon(new ImageIcon(newImg));
			}
		});

		// Centralizando a tela
		setLocationRelativeTo(null);
	}
}
