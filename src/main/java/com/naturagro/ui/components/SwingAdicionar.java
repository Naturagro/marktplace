package com.naturagro.ui.components;

import com.naturagro.controllers.CadastroProdutoController;
import com.naturagro.controllers.ControlException;

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
import java.util.Map;

public class SwingAdicionar extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private Map<JTextField, String> campos = new HashMap<>();

	public static void main(String[] args) {
	}

	public SwingAdicionar(Map<JComponent, String> camposLabels) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(124, 188, 52));
		contentPanel.setLayout(new GridLayout(camposLabels.size(), 2, 10, 10));
		setModal(true);

		for (Map.Entry<JComponent, String> entry : camposLabels.entrySet()) {
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
			buttonPane.setBackground(new Color(124, 188, 52));
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton salvarButton = new JButton("Salvar");
				salvarButton.setActionCommand("SalvarProduto");
				buttonPane.add(salvarButton);
				getRootPane().setDefaultButton(salvarButton);

				//todo: revisar essa parte das labels
				salvarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Map<String, String> valoresPreenchidos = new HashMap<>();

						//ajeitar pra ele pegar as informações da label certinho
						for (JComponent componente : campos.keySet()) {
							String label = campos.get(componente); // nome da label associada ao campo (eu acho)
							String valor = "";

							// ver se é jcombobox ou textfield
							if (componente instanceof JTextField) {
								valor = ((JTextField) componente).getText();
							} else if (componente instanceof JComboBox) {
								//converter a escolha do usuario pra string
								valor = ((JComboBox<?>) componente).getSelectedItem().toString();
							}

							// adicionando ao map criado no inicio
							valoresPreenchidos.put(label, valor);
						}
						// pegando valores específicos do Map
						String categoria = valoresPreenchidos.get("Categorias:");
						String codigo = valoresPreenchidos.get("Código:");
						String nomeProduto = valoresPreenchidos.get("Nome do Produto:");
						String preco = valoresPreenchidos.get("Preço:");
						String validade = valoresPreenchidos.get("Validade do produto:");
						String fornecedor = valoresPreenchidos.get("Fornecedor:");

						try {
							//validando os campos com controller
							CadastroProdutoController controller = new CadastroProdutoController();
							controller.registerProduto(categoria, codigo, nomeProduto, preco, validade, fornecedor);
							JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
							//todo: funcionalidade pra limpar os campos para usuario adicionar novo produto
						} catch (ControlException exception) {
							JOptionPane.showMessageDialog(salvarButton, exception.getMessage());
						}
					}
				});

			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

