package com.naturagro.ui.components;

import com.naturagro.controllers.CadastroProdutoController;
import com.naturagro.controllers.ControlException;
import com.naturagro.models.CategoriaProduto;
import com.naturagro.models.Lote;
import com.naturagro.models.Produto;
import com.naturagro.service.LoteService;
import com.naturagro.service.ProdutoService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class JDialogControleEstoque extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private Map<JTextField, String> campos = new HashMap<>();
    private JFormattedTextField dataVencimentoTxtField;

	public JDialogControleEstoque() {
        setBounds(100, 100, 550, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setBackground(new Color(124, 188, 52));
        contentPanel.setLayout(new GridLayout(campos.size(), 2, 10, 10));
        setModal(true);

        Map<JComponent, String> campos = new HashMap<>();

        try {
            // Mascara de data
            MaskFormatter dateMask = new MaskFormatter("##-##-####");
            dateMask.setPlaceholderCharacter('_');
            dateMask.setAllowsInvalid(false);
            dateMask.setOverwriteMode(true);
            dataVencimentoTxtField = new JFormattedTextField(dateMask);
            dataVencimentoTxtField.setText("__-__-__");
            dataVencimentoTxtField.setBackground(new Color(83, 131, 5));
            dataVencimentoTxtField.setHorizontalAlignment(SwingConstants.CENTER);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Instanciar todos os JComponents que a tela vai ter
        JTextField codigoDeBarrasTxtField = new JTextField();
        JTextField quantidadeTxtField = new JTextField();

        // depois é só preencher o Map com o objeto JComponent criado e a string das labels que vai explicar eles.
        campos.put(codigoDeBarrasTxtField, "Codigo de Barras:");
        campos.put(quantidadeTxtField, "Quantidade a ser Adicionada:");
        campos.put(dataVencimentoTxtField, "Data de Vencimento do Lote:");

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
        dataVencimentoTxtField.setForeground(Color.WHITE); // alterando o field de data de vencimento pq ele é diferentão
        dataVencimentoTxtField.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
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
                            ProdutoService produtoService = new ProdutoService();
                            LoteService loteService = new LoteService();

                            long codigoBarrasResult;
                            int quantidadeAdd;
                            LocalDate dataVencimentoResult = null;

                            // Validação do código de barras
                            try {
                                codigoBarrasResult = Long.parseLong(codigoDeBarrasTxtField.getText());
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Código de barras inválido! Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            // Validação da quantidade
                            try {
                                quantidadeAdd = Integer.parseInt(quantidadeTxtField.getText());
                                if (quantidadeAdd <= 0) {
                                    JOptionPane.showMessageDialog(null, "A quantidade deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Quantidade inválida! Digite um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            // Validação da data de vencimento
                            String dataVencimentoField = dataVencimentoTxtField.getText();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            try {
                                dataVencimentoResult = LocalDate.parse(dataVencimentoField, formatter);
                                if (dataVencimentoResult.isBefore(LocalDate.now())) {
                                    JOptionPane.showMessageDialog(null, "A data de vencimento não pode ser no passado!", "Erro", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                            } catch (DateTimeParseException ex) {
                                JOptionPane.showMessageDialog(null, "Data inválida! Use o formato dd-MM-yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            // Obter produto
                            Produto produto = produtoService.obterPorID(codigoBarrasResult);
                            if (produto == null) {
                                JOptionPane.showMessageDialog(null, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            // Criar e salvar lote
                            Lote lote = new Lote(produto, LocalDate.now(), quantidadeAdd, dataVencimentoResult);
                            loteService.incluirAtomico(lote);

                            JOptionPane.showMessageDialog(null, "Lote adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Erro ao salvar o lote: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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

