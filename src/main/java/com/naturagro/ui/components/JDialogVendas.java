package com.naturagro.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.naturagro.models.Produto;
import com.naturagro.service.ProdutoService;
import com.naturagro.ui.ProdutoSelecionadoListener;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class JDialogVendas extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	ProdutoService produtoService = new ProdutoService();
	TableModelListener model;
	private JTable table;
	private Map<Long, Produto> produtosAlteradosMap = new HashMap<>();
	private JTextField pesquisaTxtField;
	private JTextField quantidadeTxtField;
	private static ProdutoSelecionadoListener listener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogVendas dialog = new JDialogVendas(listener);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogVendas(ProdutoSelecionadoListener listener) {
		this.listener = listener;
		setTitle("Adicão de produtos para venda");
		setBounds(100, 100, 1280, 720);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(124, 188, 52));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		JLayeredPane camadas = new JLayeredPane();
		contentPane.add(camadas);
		camadas.setBounds(0,0,1280,720);
		contentPane.add(camadas, BorderLayout.CENTER);


		ImageIcon background2 = new ImageIcon(getClass().getResource("/images/background2edit.png"));
		JLabel backgroundLabel = new JLabel(background2);
		backgroundLabel.setBounds(0, 0, 1270, 681);
		camadas.add(backgroundLabel,Integer.valueOf(0));

		// Definindo o modelo de dados da tabela
		DefaultTableModel model = new DefaultTableModel() {
			// Definindo quais colunas serão editaveis
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Armazenando a consulta do BD na variavel
		List<Object[]> consulta = produtoService.buscarPerfilProduto();

		// Definindo um ScrollPane para colocar a tabela
		JScrollPane scrollPane = new JScrollPane();
		camadas.setLayer(scrollPane, 1);
		scrollPane.setBounds(120, 87, 1014, 490);
		camadas.add(scrollPane);

		// Adiciona ao modelo de dados as colunas que vão aparecer
		model.addColumn("ID");
		model.addColumn("Categoria");
		model.addColumn("Descrição");
		model.addColumn("Nome");
		model.addColumn("Preço");
		model.addColumn("Quantidade em Estoque:");

		// Colocando as rows no modelo de dados
		for (Object[] linha : consulta) {
			model.addRow(linha);
		}

		// Criando tabela com o modelo e setando como visivel
		table = new JTable(model);
		scrollPane.setViewportView(table);

		JLabel pesquisaLabel = new JLabel("Pesquisar Produto:");
		camadas.setLayer(pesquisaLabel, 1);
		pesquisaLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
		pesquisaLabel.setForeground(new Color(255, 255, 255));
		pesquisaLabel.setBounds(120, 44, 249, 25);
		camadas.add(pesquisaLabel);

		pesquisaTxtField = new JTextField();
		camadas.setLayer(pesquisaTxtField, 1);
		pesquisaTxtField.setBounds(370, 44, 310, 32);
		camadas.add(pesquisaTxtField);
		pesquisaTxtField.setColumns(10);

		// Listener pra detectar mudanças na barra de pesquisa
		pesquisaTxtField.getDocument().addDocumentListener(new DocumentListener() {
			@Override // cada vez que adiciona um caractere
			public void insertUpdate(DocumentEvent e) {
				onTextChanged();
			}

			@Override // cada vez que remove um caractere
			public void removeUpdate(DocumentEvent e) {
				onTextChanged();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
			// mé_todo chamado na mudança
			private void onTextChanged() {
				System.out.println("Texto atualizado: " + pesquisaTxtField.getText());

				DefaultTableModel model = (DefaultTableModel) table.getModel();

				// Remove o listener antes de modificar a tabela para evitar exceções
				TableModelListener listener = model.getTableModelListeners()[0];
				model.removeTableModelListener(listener);

				model.setRowCount(0); // Remove todas as linhas

				List<Produto> consultaNome = produtoService.buscarPorNome(pesquisaTxtField.getText());

				for (Produto produto : consultaNome) {
					Object[] linha = {
							produto.getId(),
							produto.getCategoria(),
							produto.getDescricao(),
							produto.getNome(),
							produto.getPreco(),
							produto.getQuantidadeEmEstoque()
					};
					model.addRow(linha);

					// Reinsere o listener depois de atualizar os dados
					model.addTableModelListener(listener);
				}
			}
		});

		JLabel filtrarLabel = new JLabel("Filtrar Pesquisa Por:");
		camadas.setLayer(filtrarLabel, 1);
		filtrarLabel.setForeground(Color.WHITE);
		filtrarLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
		filtrarLabel.setBounds(690, 44, 272, 25);
		camadas.add(filtrarLabel);

		JComboBox<String> filtroBox = new JComboBox();
		filtroBox.setModel(new DefaultComboBoxModel<>(new String[] {"Nome","ID"}));
		filtroBox.setBackground(new Color(83, 131, 5));
		filtroBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		filtroBox.setForeground(Color.WHITE);
		camadas.setLayer(filtroBox, 1);
		filtroBox.setBounds(961, 44, 173, 32);
		camadas.add(filtroBox);

		JLabel quantidadeLabel = new JLabel("Quantidade:");
		camadas.setLayer(quantidadeLabel, 1);
		quantidadeLabel.setForeground(Color.WHITE);
		quantidadeLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
		quantidadeLabel.setBounds(120, 588, 249, 25);
		camadas.add(quantidadeLabel);

		quantidadeTxtField = new JTextField("1");
		camadas.setLayer(quantidadeTxtField, 1);
		quantidadeTxtField.setColumns(10);
		quantidadeTxtField.setBounds(300, 588, 45, 32);
		camadas.add(quantidadeTxtField);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			contentPane.add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Adicionar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						// Pega a linha selecionada
						int linha = table.getSelectedRow();
                        Long id = null;

                        if (linha != -1) {
							// com base na linha pega o id do produto selecionado
							String celula = table.getValueAt(linha, 0).toString();
							id = Long.parseLong(celula);
							// coloca o produto nessa variavel
							Produto produtoAdicionado = produtoService.obterPorID(id);

							if (listener != null) {
								// Manda pra o SwingVendas através da interface listener
								Integer quantidade = Integer.parseInt(quantidadeTxtField.getText());
								listener.onProdutoSelecionado(produtoAdicionado,quantidade);
							}
						}
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
		}
	}
}
