package com.company;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private InformationPanel informationPanel;

    public MainPanel(Model model) {
        super();

        // Aufteilung des Fensters in Interaktionspanel und Koordinatensystem
        informationPanel = new InformationPanel(this, model);
        GraphicsPanel graphicsPanel = new GraphicsPanel(model, informationPanel);
        GridBagConstraints gbc = new GridBagConstraints();

        this.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.2;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(informationPanel, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.8;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(graphicsPanel, gbc);
    }

    public InformationPanel getInformationPanel() {
        return informationPanel;
    }
}
