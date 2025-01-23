package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingPaginaInicial extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField textField;

    // Criando a Tela
    public SwingPaginaInicial() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Página Inicial");

        setUndecorated(false);
        // Localização e Tamanho
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width,screenSize.height);

        // Definição de Layout
        getContentPane().setLayout(null);

        // Forçar tela cheia
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
    }
}
