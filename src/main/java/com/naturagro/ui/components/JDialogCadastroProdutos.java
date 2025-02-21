package com.naturagro.ui.components;

import com.naturagro.controllers.CadastroProdutoController;
import com.naturagro.controllers.ControlException;
import com.naturagro.models.CategoriaProduto;
import com.naturagro.models.Produto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class JDialogCadastroProdutos extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private Map<JTextField, String> campos = new HashMap<>();

	public static void main(String[] args) {
		JDialogCadastroProdutos teste = new JDialogCadastroProdutos();
		teste.setVisible(true);
	}

	public JDialogCadastroProdutos() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(124, 188, 52));
		contentPanel.setLayout(new GridLayout(campos.size(), 2, 10, 10));
		setModal(true);

		Map<JComponent, String> campos = new LinkedHashMap<>();

		// Instanciar todos os JComponents que a tela vai ter
		JComboBox<CategoriaProduto> categoria = new JComboBox<>(CategoriaProduto.values());
		JTextField nomeProduto = new JTextField();
		JTextField preco = new JTextField();
		JTextField descricao = new JTextField();

		// depois é só preencher o Map com o objeto JComponent criado e a string das labels que vai explicar eles.
		campos.put(categoria,"Categorias:");
		campos.put(nomeProduto, "Nome do Produto:");
		campos.put(descricao,"Descrição do produto:");
		campos.put(preco,"Preço:");

		// Monta a configuração de exibição da janela com base nos que setado acima
		for (Map.Entry<JComponent, String> entry : campos.entrySet()) {
			JComponent componente = entry.getKey();
			String labelTexto = entry.getValue();

			JLabel lbl = new JLabel(labelTexto);
			lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 21));
			lbl.setForeground(new Color(255, 255, 255));
			contentPanel.add(lbl);

			contentPanel.add(componente);
		}

		{
			// Area relativa aos botões
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(96, 145, 2));
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				// Definindo o botão Salvar
				JButton salvarButton = new JButton("Salvar");
				salvarButton.setActionCommand("SalvarProduto");
				buttonPane.add(salvarButton);
				getRootPane().setDefaultButton(salvarButton);

				salvarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {

							Produto produto = new Produto(
									nomeProduto.getText(),
									descricao.getText(),
									Double.parseDouble(preco.getText()),
									(CategoriaProduto) categoria.getSelectedItem());

							// Chamando o controlador de cadastro para registrar o produto no BD após as validações de campo
							CadastroProdutoController controller = new CadastroProdutoController();
							controller.registerProduto(produto);

							JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
							// Limpando Campos Após adição
							nomeProduto.setText("");
							descricao.setText("");
							preco.setText("");

						} catch (ControlException exception) {
							JOptionPane.showMessageDialog(salvarButton, exception.getMessage());
						} catch (NumberFormatException exception) {
							JOptionPane.showMessageDialog(salvarButton, "Os preços devem ser valores numéricos!");
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

