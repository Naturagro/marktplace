package com.naturagro.ui.components;

import com.naturagro.controllers.CadastroProdutoController;
import com.naturagro.controllers.ControlException;
import com.naturagro.models.CategoriaProduto;
import com.naturagro.models.Produto;
import com.naturagro.service.ProdutoService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class JDialogControleEstoque extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private Map<JTextField, String> campos = new HashMap<>();
	private boolean isAdding;

	public JDialogControleEstoque(boolean isAdding) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(124, 188, 52));
		contentPanel.setLayout(new GridLayout(campos.size(), 2, 10, 10));
		setModal(true);

		Map<JComponent, String> campos = new HashMap<>();

		// Instanciar todos os JComponents que a tela vai ter
		JTextField codigoDeBarrasTxtField = new JTextField();
		JTextField quantidadeTxtField = new JTextField();

		// depois é só preencher o Map com o objeto JComponent criado e a string das labels que vai explicar eles.
		campos.put(codigoDeBarrasTxtField, "Codigo de Barras:");
		campos.put(quantidadeTxtField, "Quantidade a ser Adicionada:");

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
                        if (isAdding) {
                            ProdutoService produtoService = new ProdutoService();

                            long codigoBarrasResult = Long.parseLong(codigoDeBarrasTxtField.getText());
                            int quantidadeAdd = Integer.valueOf(quantidadeTxtField.getText());

                            Produto produtoAlterar = produtoService.obterPorID(codigoBarrasResult);
                            int quantidadeAtual = produtoAlterar.getQuantidadeEmEstoque();

                            produtoAlterar.setQuantidadeEmEstoque(quantidadeAtual+quantidadeAdd);

                            produtoService.abrirT();
                            produtoService.mesclar(produtoAlterar);
                            produtoService.fecharT();

                            System.out.println("Quantidade atual: "+quantidadeAtual+" /Quantidade Add: "+quantidadeAdd+" /Quantidade Final: "+(quantidadeAtual+quantidadeAdd));

                            JOptionPane.showMessageDialog(null, "Estoque adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        } else {
							ProdutoService produtoService = new ProdutoService();

							long codigoBarrasResult = Long.parseLong(codigoDeBarrasTxtField.getText());
							int quantidadeRemove = Integer.valueOf(quantidadeTxtField.getText());

							Produto produtoAlterar = produtoService.obterPorID(codigoBarrasResult);
							int quantidadeAtual = produtoAlterar.getQuantidadeEmEstoque();

							produtoAlterar.setQuantidadeEmEstoque(quantidadeAtual-quantidadeRemove);

							produtoService.abrirT();
							produtoService.mesclar(produtoAlterar);
							produtoService.fecharT();

							System.out.println("Quantidade atual: "+quantidadeAtual+" /Quantidade Removida: "+quantidadeRemove+" /Quantidade Final: "+(quantidadeAtual+quantidadeRemove));

							JOptionPane.showMessageDialog(null, "Estoque removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
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

