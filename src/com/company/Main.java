package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        // Erstellung eines neuen Fensters
        JFrame frame = new JFrame("Numerische Integration");
        // MVC Pattern
        Model model = new Model();
        MainPanel mp = new MainPanel(model);

        frame.add(mp);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(576, 324));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
