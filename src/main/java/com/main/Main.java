package com.main;

import com.naturagro.bd.PopularBD;
import com.naturagro.ui.UI;

public class Main {
    public static void main(String[] args) {
        System.out.println("O projeto começará aqui");

        PopularBD popularBD = new PopularBD();
        popularBD.popular();
        UI ui = new UI();
        ui.GUIIInit();
    }
}
