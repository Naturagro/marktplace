package com.naturagro.ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.naturagro.models.Lote;
import com.naturagro.models.Produto;
import com.naturagro.service.LoteService;
import com.naturagro.service.ProdutoService;
import com.naturagro.ui.ProdutoSelecionadoListener;

import java.awt.Font;

public class JDialogControleEstoque extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    ProdutoService produtoService = new ProdutoService();
    TableModelListener model;
    private JTable table;
    private Map<Long, Produto> produtosAlteradosMap = new HashMap<>();
    private JTextField pesquisaTxtField;
    private JTextField codigoDeBarrasTxtField;
    private static ProdutoSelecionadoListener listener;
    JComboBox<String> filtroBox = new JComboBox();
    private JTextField dataVencimentoTxtField;
    private JTextField quantidadeTxtField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            JDialogControleEstoque dialog = new JDialogControleEstoque();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public JDialogControleEstoque() {
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

        // Definindo um ScrollPane para colocar a tabela
        JScrollPane scrollPane = new JScrollPane();
        camadas.setLayer(scrollPane, 1);
        scrollPane.setBounds(120, 87, 1014, 408);
        camadas.add(scrollPane);

        // Adiciona ao modelo de dados as colunas que vão aparecer
        model.addColumn("Código");
        model.addColumn("Categoria");
        model.addColumn("Descrição");
        model.addColumn("Nome");
        model.addColumn("Preço");

        // Armazenando a consulta do BD na variavel
        List<Produto> consulta = produtoService.obterTodos(Integer.MAX_VALUE, 0);

        // Armazenando a consulta do BD na variavel
        for (Produto linha : consulta) {
            model.addRow(new Object[]{
                    linha.getId(),
                    linha.getCategoria(),
                    linha.getDescricao(),
                    linha.getNome(),
                    linha.getPreco(),
            });
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
                atualizarTabela();
            }
        });

        JLabel filtrarLabel = new JLabel("Filtrar Pesquisa Por:");
        camadas.setLayer(filtrarLabel, 1);
        filtrarLabel.setForeground(Color.WHITE);
        filtrarLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
        filtrarLabel.setBounds(690, 44, 272, 25);
        camadas.add(filtrarLabel);

        // ComboBox
        filtroBox.setModel(new DefaultComboBoxModel<>(new String[] {"Nome","Código"}));
        filtroBox.setBackground(new Color(83, 131, 5));
        filtroBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        filtroBox.setForeground(Color.WHITE);
        camadas.setLayer(filtroBox, 1);
        filtroBox.setBounds(961, 44, 173, 32);
        camadas.add(filtroBox);

        JLabel codigoLabel = new JLabel("Código:");
        camadas.setLayer(codigoLabel, 1);
        codigoLabel.setForeground(Color.WHITE);
        codigoLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
        codigoLabel.setBounds(120, 506, 145, 51);
        camadas.add(codigoLabel);

        codigoDeBarrasTxtField = new JTextField("1");
        codigoDeBarrasTxtField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        camadas.setLayer(codigoDeBarrasTxtField, 1);
        codigoDeBarrasTxtField.setColumns(10);
        codigoDeBarrasTxtField.setBounds(252, 507, 129, 51);
        camadas.add(codigoDeBarrasTxtField);

        JLabel dataVencimentoLabel = new JLabel("Data de Vencimento:");
        dataVencimentoLabel.setForeground(Color.WHITE);
        dataVencimentoLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
        dataVencimentoLabel.setBounds(120, 568, 347, 51);
        camadas.setLayer(dataVencimentoLabel, 1);
        camadas.add(dataVencimentoLabel);

        try {
            MaskFormatter dateMask = new MaskFormatter("##-##-####");
            dateMask.setPlaceholderCharacter('_');
            dateMask.setAllowsInvalid(false);
            dateMask.setOverwriteMode(true);
            dataVencimentoTxtField = new JFormattedTextField(dateMask);
            dataVencimentoTxtField.setText("__-__-____");
            dataVencimentoTxtField.setBackground(new Color(83, 131, 5));
            dataVencimentoTxtField.setHorizontalAlignment(SwingConstants.CENTER);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dataVencimentoTxtField.setFont(new Font("Tahoma", Font.PLAIN, 28));
        dataVencimentoTxtField.setColumns(10);
        dataVencimentoTxtField.setBounds(477, 568, 261, 51);
        camadas.setLayer(dataVencimentoTxtField, 1);
        camadas.add(dataVencimentoTxtField);

        quantidadeTxtField = new JTextField("1");
        quantidadeTxtField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        quantidadeTxtField.setColumns(10);
        quantidadeTxtField.setBounds(609, 506, 129, 51);
        camadas.setLayer(quantidadeTxtField, 1);
        camadas.add(quantidadeTxtField);

        JLabel QuantidadeLabel = new JLabel("Quantidade:");
        QuantidadeLabel.setForeground(Color.WHITE);
        QuantidadeLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
        QuantidadeLabel.setBounds(391, 506, 222, 51);
        camadas.setLayer(QuantidadeLabel, 1);
        camadas.add(QuantidadeLabel);

        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            contentPane.add(buttonPane, BorderLayout.SOUTH);
            {
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
                            LocalDate dataVencimentoResult;

                            try {
                                codigoBarrasResult = Long.parseLong(codigoDeBarrasTxtField.getText());
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Código de barras inválido! Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

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

                            Produto produto = produtoService.obterPorID(codigoBarrasResult);
                            if (produto == null) {
                                JOptionPane.showMessageDialog(null, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

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

    private void atualizarTabela() {
        ProdutoService produtoService = new ProdutoService();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Remove o listener antes de modificar a tabela para evitar exceções
        TableModelListener listener = model.getTableModelListeners()[0];
        model.removeTableModelListener(listener);

        model.setRowCount(0); // Remove todas as linhas

        if (filtroBox.getSelectedItem() == "Nome") {
            List<Produto> consultaNome = produtoService.buscarPorNome(pesquisaTxtField.getText());

            for (Produto produto : consultaNome) {
                Object[] linha = {
                        produto.getId(),
                        produto.getCategoria(),
                        produto.getDescricao(),
                        produto.getNome(),
                        produto.getPreco(),
                };
                model.addRow(linha);

                // Reinsere o listener depois de atualizar os dados
                model.addTableModelListener(listener);
            }
        } else {
            List<Produto> consultaNome = produtoService.buscarPorId(pesquisaTxtField.getText());

            for (Produto produto : consultaNome) {
                Object[] linha = {
                        produto.getId(),
                        produto.getCategoria(),
                        produto.getDescricao(),
                        produto.getNome(),
                        produto.getPreco(),
                };
                model.addRow(linha);

                // Reinsere o listener depois de atualizar os dados
                model.addTableModelListener(listener);
            }
        }

        // Notifica a tabela que os dados mudaram
        model.fireTableDataChanged();
    }
}
