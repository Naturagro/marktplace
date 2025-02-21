package com.naturagro.ui.components;

import com.naturagro.payment.http.PostRequest;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.Map;

public class JDialogPagamento extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> tipoPagamento;
	private JTextField dinheiroPago;
	private JLabel totalPagar, troco, status;
	private Map<JComponent, String> dinheiro = new LinkedHashMap<>();
	private Map<JComponent, String> cartao = new LinkedHashMap<>();
	private String valorTotal;
	private boolean pagamentoConfirmado = false;

	public static void main(String[] args) {
		JDialogPagamento teste = new JDialogPagamento("115");
		teste.setVisible(true);
	}

	public JDialogPagamento(String valorTotal) {
		this.valorTotal = valorTotal;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(124, 188, 52));
		contentPanel.setLayout(new GridLayout(0, 2, 10, 10));
		setModal(true);

		// Criando componentes
		tipoPagamento = new JComboBox<>(new String[]{"Dinheiro", "Cartão"});
		dinheiroPago = new JTextField();
		totalPagar = new JLabel(valorTotal);
		totalPagar.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		totalPagar.setForeground(Color.white);
		troco = new JLabel("0.0");
		troco.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		troco.setForeground(Color.white);
		status = new JLabel("Aguardando...");

		// Configurando os mapas de pagamento
		dinheiro.put(totalPagar, "Total a Pagar:");
		dinheiro.put(dinheiroPago, "Dinheiro Pago:");
		dinheiro.put(troco, "Troco:");

		cartao.put(totalPagar, "Total a Pagar:");

		// Adicionando ActionListener à ComboBox
		tipoPagamento.addActionListener(e -> atualizarCampos());

		// Inicializando a interface com os campos corretos
		atualizarCampos();

		// Criando área de botões
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(96, 145, 2));
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton finalizarButton = new JButton("Finalizar");
		finalizarButton.setActionCommand("FinalizarVenda");
		buttonPane.add(finalizarButton);
		getRootPane().setDefaultButton(finalizarButton);
		finalizarButton.addActionListener(e -> finalizarPagamento());

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		cancelButton.addActionListener(e -> dispose());

		// Adicionando DocumentListener ao campo "dinheiroPago" para atualizar o troco
		dinheiroPago.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				atualizarTroco();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				atualizarTroco();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				atualizarTroco();
			}
		});
	}

	private void atualizarCampos() {
		contentPanel.removeAll(); // Remove os componentes antigos
		JLabel formaPagamento = new JLabel("Forma de Pagamento:");
		formaPagamento.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		formaPagamento.setForeground(Color.WHITE);
		contentPanel.add(formaPagamento);
		contentPanel.add(tipoPagamento);

		// Determina quais componentes adicionar
		Map<JComponent, String> camposAtuais = tipoPagamento.getSelectedItem().equals("Dinheiro") ? dinheiro : cartao;

		for (Map.Entry<JComponent, String> entry : camposAtuais.entrySet()) {
			JLabel lbl = new JLabel(entry.getValue());
			lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
			lbl.setForeground(Color.WHITE);
			contentPanel.add(lbl);
			contentPanel.add(entry.getKey());
		}

		// Atualiza o layout
		contentPanel.revalidate();
		contentPanel.repaint();
	}

	private void atualizarTroco() {
		try {
			// Pegando o valor pago e o valor total
			double total = Double.parseDouble(valorTotal.replace("R$", "").trim());
			double pago = Double.parseDouble(dinheiroPago.getText().replace(",", ".").trim());

			// Calculando o troco
			double trocoValue = pago - total;

			// Atualizando o campo de troco
			troco.setText(String.format("%.2f", trocoValue));
			troco.setForeground(trocoValue < 0 ? Color.RED : Color.GREEN);
		} catch (NumberFormatException ex) {
			troco.setText("0.0");
			troco.setForeground(Color.RED);
		}
	}

	private void finalizarPagamento() {
		try {
			double total = Double.parseDouble(valorTotal.replace("R$", "").trim());

			if (tipoPagamento.getSelectedItem().equals("Cartão")) {
				new Thread(() -> {
					try {
						salvarPedido();
						PostRequest.realizarPagamento();
						JOptionPane.showMessageDialog(null, "Pagamento realizado com sucesso!");
						confirmarPagamento();
						dispose();
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao processar pagamento: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					}
				}).start();
			} else {
				double pago = Double.parseDouble(dinheiroPago.getText().replace(",", ".").trim());

				if (pago < total) {
					JOptionPane.showMessageDialog(null, "O dinheiro pago é menor que o valor da compra!");
				} else {
					JOptionPane.showMessageDialog(null, "Pagamento realizado com sucesso!");
					confirmarPagamento();
					dispose();
				}
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Erro: Valor inválido inserido. Insira apenas números!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void salvarPedido() {
		try {
			double valorTotalDouble = Double.parseDouble(valorTotal.replace("R$", "").trim());

			JSONObject pedido = new JSONObject();
			pedido.put("payment_method_id", "visa");
			pedido.put("installments", 1);
			pedido.put("transaction_amount", valorTotalDouble);
			pedido.put("description", "Compra de Teste");

			JSONObject payer = new JSONObject();
			payer.put("email", "teste@gmail.com");
			pedido.put("payer", payer);

			Path path = Path.of("src/main/java/com/naturagro/payment/http/pedido.json");
			Files.writeString(path, pedido.toString(4), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

			System.out.println("Pedido salvo no JSON com sucesso!");
		} catch (IOException e) {
			System.err.println("Erro ao salvar pedido.json: " + e.getMessage());
		}
	}

	public boolean isPagamentoConfirmado() {
		return pagamentoConfirmado;
	}

	private void confirmarPagamento() {
		pagamentoConfirmado = true; // Seta como confirmado ao finalizar o pagamento
		dispose(); // Fecha o diálogo
	}

}
