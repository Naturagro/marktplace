package com.naturagro.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

public class SwingAdicionar extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
    private Map<JTextField, String> campos = new HashMap<>();

	public SwingAdicionar(Map<JTextField, String> camposLabels) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(124, 188, 52));
        contentPanel.setLayout(new GridLayout(camposLabels.size(), 2, 10, 10));
		setModal(true);
		
		 for (Map.Entry<JTextField, String> entry : camposLabels.entrySet()) {
	            JTextField nomeVariavel = entry.getKey();
	            String labelTexto = entry.getValue();

	            JLabel lbl = new JLabel(labelTexto);		
	            lbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 21));
	    		lbl.setForeground(new Color(255, 255, 255));
	            contentPanel.add(lbl);

	            contentPanel.add(nomeVariavel);
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
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
