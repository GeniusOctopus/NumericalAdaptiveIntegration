package com.company;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {

    Controller controller;
    JLabel functionLabel;
    JLabel functionText;
    JCheckBox shouldDrawFunction;
    JLabel trapezoidalRule;
    JLabel intervalNumberLabel;
    JTextField intervalNumber;
    JButton startTrapezoidalRule;
    JLabel trapezoidalResult;
    JCheckBox shouldDrawTrapezoidalIntervals;
    JLabel adaptiveIntegration;
    JTextField intervalNumberAdaptive;
    JButton startAdaptiveIntegration;
    JLabel adaptiveIntegrationResult;
    JCheckBox shouldDrawAdaptiveIntervals;

    public InformationPanel(MainPanel mp, Model model) {
        super();

        controller = new Controller(mp, model);
        this.initialise();
    }

    private void initialise() {

        // Initialisierung des Panels
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Initialisierung der Steuerelemente
        functionLabel = new JLabel("Zu untersuchende Funktion:");
        functionLabel.setFont(new Font(functionLabel.getFont().getName(), Font.PLAIN, 20));

        functionText = new JLabel();
        functionText.setText("f(x) = e^-(x-2)^2/0,05 + e^-(x-5)^2/0,1 + 0,1x - e^-(x-3,5)^2/0,1");
        functionText.setFont(new Font(functionText.getFont().getName(), Font.PLAIN, 14));

        shouldDrawFunction = new JCheckBox();
        shouldDrawFunction.setText("Funktion einzeichnen");
        shouldDrawFunction.setActionCommand("toggleFunction");
        shouldDrawFunction.addActionListener(controller);

        trapezoidalRule = new JLabel("Berechnung der Fläche mittels Trapezregel:");
        trapezoidalRule.setFont(new Font(trapezoidalRule.getFont().getName(), Font.PLAIN, 20));

        intervalNumberLabel = new JLabel("Teilintervalle n = ");
        intervalNumberLabel.setFont(new Font(intervalNumberLabel.getFont().getName(), Font.PLAIN, 14));

        intervalNumber = new JTextField("10");
        intervalNumber.setPreferredSize(new Dimension(10, 20));

        startTrapezoidalRule = new JButton("Start");
        startTrapezoidalRule.setActionCommand("startTrapezoidalRule");
        startTrapezoidalRule.addActionListener(controller);

        trapezoidalResult = new JLabel(" ");
        trapezoidalResult.setFont(new Font(trapezoidalResult.getFont().getName(), Font.PLAIN, 14));

        shouldDrawTrapezoidalIntervals = new JCheckBox();
        shouldDrawTrapezoidalIntervals.setText("Trapeze einzeichnen");
        shouldDrawTrapezoidalIntervals.setActionCommand("toggleTrapezoidals");
        shouldDrawTrapezoidalIntervals.addActionListener(controller);

        adaptiveIntegration = new JLabel("Berechnung der Fläche mittels adaptiver Integration:");
        adaptiveIntegration.setFont(new Font(adaptiveIntegration.getFont().getName(), Font.PLAIN, 20));

        intervalNumberAdaptive = new JTextField("10");
        intervalNumberAdaptive.setPreferredSize(new Dimension(10, 20));

        startAdaptiveIntegration = new JButton("Start");
        startAdaptiveIntegration.setActionCommand("startAdaptiveIntegration");
        startAdaptiveIntegration.addActionListener(controller);

        adaptiveIntegrationResult = new JLabel(" ");
        adaptiveIntegrationResult.setFont(new Font(adaptiveIntegrationResult.getFont().getName(), Font.PLAIN, 14));

        shouldDrawAdaptiveIntervals = new JCheckBox();
        shouldDrawAdaptiveIntervals.setText("Trapeze einzeichnen");
        shouldDrawAdaptiveIntervals.setActionCommand("toggleAdaptiveIntervals");
        shouldDrawAdaptiveIntervals.addActionListener(controller);

        // Layout
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(functionLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(functionText, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(shouldDrawFunction, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(new JLabel(" "), gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(trapezoidalRule, gbc);
        gbc.gridx = 0;
        gbc.weightx = 0.3;
        gbc.gridy = 5;
        this.add(intervalNumberLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.gridy = 5;
        this.add(intervalNumber, gbc);
        gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        gbc.gridx = 0;
        gbc.gridy = 6;
        this.add(startTrapezoidalRule, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        this.add(trapezoidalResult, gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        this.add(shouldDrawTrapezoidalIntervals, gbc);
        gbc.gridx = 0;
        gbc.gridy = 9;
        this.add(new JLabel(" "), gbc);
        gbc.gridx = 0;
        gbc.gridy = 10;
        this.add(adaptiveIntegration, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.gridy = 10;
        this.add(intervalNumberAdaptive, gbc);
        gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        gbc.gridx = 0;
        gbc.gridy = 11;
        this.add(startAdaptiveIntegration, gbc);
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        this.add(adaptiveIntegrationResult, gbc);
        gbc.gridx = 0;
        gbc.gridy = 13;
        this.add(shouldDrawAdaptiveIntervals, gbc);
    }

    public void setTrapezoidalResult(String s) {
        this.trapezoidalResult.setText(s);
    }

    public JTextField getIntervalNumber() {
        return intervalNumber;
    }

    public void setAdaptiveIntegrationResult(String s) {
        this.adaptiveIntegrationResult.setText(s);
    }

    public JTextField getIntervalNumberAdaptive() {
        return intervalNumberAdaptive;
    }
}
