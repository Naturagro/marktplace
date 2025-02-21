package com.main;

import com.naturagro.bd.PopularBD;
import com.naturagro.ui.Gui;

public class Main {
    public static void main(String[] args) {
        System.out.println("O projeto começará aqui");

        PopularBD popularBD = new PopularBD();
        popularBD.popular();

        // A interface será iniciada com esse mé_todo
        Gui gui = new Gui();
        gui.startGui();
    }
}
