package com.company;

import javax.swing.*;
import java.awt.*;

public class GraphicsPanel extends JPanel {

    private CoordinateGrid coordinateGrid;

    // Container für das Koordinatensystem
    public GraphicsPanel(Model model, InformationPanel informationPanel) {
        super();

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        coordinateGrid = new CoordinateGrid(model, informationPanel);
        this.setLayout(new BorderLayout());

        this.add(coordinateGrid, BorderLayout.CENTER);
    }
}
