package com.naturagro.ui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.naturagro.models.Produto;
import com.naturagro.ui.ControladorSwing;
import com.naturagro.ui.ProdutoSelecionadoListener;

public class SwingVendas extends JFrame {
	private static final long serialVersionUID = 1L;
	private ControladorSwing controlador;
	private JLabel backgroundLabel;
	private JTable table;

	public SwingVendas(ControladorSwing controladorDeTela) {
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

		// Título "Vendas"
		JLabel VendasLabel = new JLabel("Vendas");
		VendasLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
		VendasLabel.setForeground(new Color(255, 255, 255));  // Cor branca para contraste
		innerGbc.gridx = 0;
		innerGbc.gridy = 0;
		innerGbc.gridwidth = 4;
		panel.add(VendasLabel, innerGbc);

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
						// Adiciona o produto e a quantidade pego pela interface no JDialog na tabela
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.addRow(new Object[]{
								produto.getId(),
								produto.getNome(),
								produto.getPreco(),
								quantidade,
								String.format("R$ %.2f", produto.getPreco() * quantidade)
						});
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

		// Botão Voltar
		JButton BotaoVoltar = new JButton("Voltar");
		BotaoVoltar.setBackground(new Color(83, 131, 5));;
		BotaoVoltar.setForeground(new Color(255, 255, 255));
		BotaoVoltar.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

		// Função do botão voltar
		BotaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladorDeTela.abrirJanela("menuPrincipal");
			}
		});

		// Tabela
		// Definindo o modelo de dados da tabela
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Bloqueia a edição da linha "Total a Pagar"
				if (row == getRowCount() - 1) {
					return false;
				}
				return false;
			}

			@Override
			public void removeRow(int row) {
				// Impede remover a linha de total
				if (row != getRowCount() - 1) {
					super.removeRow(row);
				}
			}
		};

		// Adiciona ao modelo de dados as colunas que vão aparecer
		model.addColumn("ID");
		model.addColumn("Produto");
		model.addColumn("Preço em R$ P/Unidade");
		model.addColumn("Quantidade");
		model.addColumn("Total");

		// Linha fixa de Total a Pagar
		model.addRow(new Object[]{"Total a Pagar", "", "", "", "R$ 0.00"});


		// Criando a tabela e colocando no JScrollPane
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);

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
			}
		});

		// Botões (com exceção do voltar)
		innerGbc.gridwidth = 1;
		innerGbc.gridx = 0;
		innerGbc.gridy = 1;
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
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent componentEvent) {
				Image img = backgroundIcon.getImage();
				Image newImg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
				backgroundLabel.setIcon(new ImageIcon(newImg));
			}
		});

		// Centralizando a tela
		setLocationRelativeTo(null);
	}
}
