package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingCredencial extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textField;

    // criando frame
    public SwingCredencial() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 597, 399);

        // liberando layout
        getContentPane().setLayout(null);

        //mudando a cor de fundo
        getContentPane().setBackground(Color.decode("#487307"));

        // adicionando e ajustando a logo
        ImageIcon imageIcon = new ImageIcon("/swing/images/logonaturagro.png");
        JLabel label = new JLabel(imageIcon);
        label.setBounds(237, 43, imageIcon.getIconWidth(), imageIcon.getIconHeight());

        // adicionando o jlabel com a imagem no jframe
        getContentPane().add(label);

        // criando e configurando botão da tela de login
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnEntrar.setBounds(232, 268, 127, 30);
        btnEntrar.setFont(new Font("Courier New", Font.PLAIN, 16));
        getContentPane().add(btnEntrar);

        // caixa de texto no painel de login
        textField = new JTextField();
        textField.setBounds(197, 214, 193, 30);
        getContentPane().add(textField);
        textField.setColumns(10);

        // criando e configurando texto da tela de login
        JLabel lbTextoCredencial = new JLabel("Digite a credencial de acesso");
        lbTextoCredencial.setBounds(92, 138, 451, 76);
        lbTextoCredencial.setFont(new Font("Courier New", Font.PLAIN, 24));
        lbTextoCredencial.setForeground(Color.WHITE);
        getContentPane().add(lbTextoCredencial);
    }
}
