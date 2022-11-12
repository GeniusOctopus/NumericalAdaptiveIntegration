package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private MainPanel view;
    private Model model;

    public Controller(MainPanel view, Model model) {

        this.view = view;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Abfangen von Knopfdrücken und entsprechende Reaktion
        switch (e.getActionCommand()) {
            case "toggleFunction":

                model.setShouldDrawFunction(!model.isShouldDrawFunction());
                view.repaint();
                break;
            case "startTrapezoidalRule":

                view.getInformationPanel().setTrapezoidalResult("Ergebnis: " + (new NumericalCalculation().trapezoidalRule(Integer.parseInt(view.getInformationPanel().getIntervalNumber().getText()))));
                view.repaint();
                break;
            case "toggleTrapezoidals":

                model.setShouldDrawTrapezoid(!model.isShouldDrawTrapezoid());
                view.repaint();
                break;
            case "startAdaptiveIntegration":

                view.getInformationPanel().setAdaptiveIntegrationResult("Ergebnis: " + (new NumericalCalculation().adaptiveAlgorithm(NumericalCalculation.LOWER_BOUND, NumericalCalculation.UPPER_BOUND, Integer.parseInt(view.getInformationPanel().getIntervalNumberAdaptive().getText()))));
                view.repaint();
                break;
            case "toggleAdaptiveIntervals":

                model.setShouldDrawAdaptiveIntervals(!model.isShouldDrawAdaptiveIntervals());
                view.repaint();
                break;
        }
    }
}
