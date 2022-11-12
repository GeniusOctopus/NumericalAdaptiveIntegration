package com.company;

import javax.swing.*;
import java.awt.*;

public class CoordinateGrid extends JPanel {

    private int coordinateOriginX;
    private int coordinateOriginY;
    private double scalingFactorX = 1.0;
    private double scalingFactorY = 1.0;
    private Model model;
    private InformationPanel informationPanel;

    public CoordinateGrid(Model m, InformationPanel informationPanel) {

        this.model = m;
        this.informationPanel = informationPanel;
    }

    public void drawFunction(Graphics2D g2d) {

        // Einzeichnen der Funktion

        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));

        for (double x = 0.0; x > -(coordinateOriginX - 10) / (100.0 * scalingFactorX); x -= 0.001) {

            double y = function(x);
            g2d.drawLine((int) (coordinateOriginX + x * 100 * scalingFactorX), (int) (coordinateOriginY - y * 100 * scalingFactorY), (int) (coordinateOriginX + x * 100 * scalingFactorX), (int) (coordinateOriginY - y * 100 * scalingFactorY));
        }

        for (double x = 0.0; x < (coordinateOriginX - 10) / (100.0 * scalingFactorX); x += 0.001) {

            double y = function(x);
            g2d.drawLine((int) (x * 100 * scalingFactorX + coordinateOriginX), (int) (coordinateOriginY - y * 100 * scalingFactorY), (int) (x * 100 * scalingFactorX + coordinateOriginX), (int) (coordinateOriginY - y * 100 * scalingFactorY));
        }
    }

    public void drawTrapezoidalIntervals(Graphics2D g2d) {

        // Einzeichnen der Trapeze in n Teilintervallen
        int n = Integer.parseInt(informationPanel.getIntervalNumber().getText());

        double distance = (NumericalCalculation.UPPER_BOUND - NumericalCalculation.LOWER_BOUND) / n;
        double a = NumericalCalculation.LOWER_BOUND;
        double b = a + distance;

        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(1));

        for (int i = 0; i < n; i++) {


            double ay = function(a);
            g2d.drawLine((int) (coordinateOriginX + a * 100 * scalingFactorX), coordinateOriginY, (int) (coordinateOriginX + a * 100 * scalingFactorX), (int) (coordinateOriginY - ay * 100 * scalingFactorY));

            double by = function(b);
            g2d.drawLine((int) (coordinateOriginX + b * 100 * scalingFactorX), coordinateOriginY, (int) (coordinateOriginX + b * 100 * scalingFactorX), (int) (coordinateOriginY - by * 100 * scalingFactorY));

            g2d.drawLine((int) (coordinateOriginX + a * 100 * scalingFactorX), (int) (coordinateOriginY - ay * 100 * scalingFactorY), (int) (coordinateOriginX + b * 100 * scalingFactorX), (int) (coordinateOriginY - by * 100 * scalingFactorY));

            a += distance;
            b += distance;
        }
    }

    public void drawAdaptiveIntervals(Graphics2D g2d) {

        // Zeichnen der Trapeze in adaptiven Intervallen
        g2d.setColor(Color.GREEN);
        g2d.setStroke(new BasicStroke(1));

        if(NumericalCalculation.refinedIntervals != null) {
            int n = NumericalCalculation.refinedIntervals.size();

            double distance = (NumericalCalculation.UPPER_BOUND - NumericalCalculation.LOWER_BOUND) / n;
            double a = NumericalCalculation.LOWER_BOUND;
            double b = a + distance;

            for (int i = 0; i < n; i++) {

                int m = NumericalCalculation.refinedIntervals.get(i);

                double refinedDistance = (b - a) / m;
                double an = a;
                double bn = an + refinedDistance;

                for(int j = 0; j < m; j++) {

                    double ay = function(an);
                    g2d.drawLine((int) (coordinateOriginX + an * 100 * scalingFactorX), coordinateOriginY, (int) (coordinateOriginX + an * 100 * scalingFactorX), (int) (coordinateOriginY - ay * 100 * scalingFactorY));

                    double by = function(bn);
                    g2d.drawLine((int) (coordinateOriginX + bn * 100 * scalingFactorX), coordinateOriginY, (int) (coordinateOriginX + bn * 100 * scalingFactorX), (int) (coordinateOriginY - by * 100 * scalingFactorY));

                    g2d.drawLine((int) (coordinateOriginX + an * 100 * scalingFactorX), (int) (coordinateOriginY - ay * 100 * scalingFactorY), (int) (coordinateOriginX + bn * 100 * scalingFactorX), (int) (coordinateOriginY - by * 100 * scalingFactorY));

                    an += refinedDistance;
                    bn += refinedDistance;
                }

                a += distance;
                b += distance;
            }
        }
    }

    private void drawGrid(Graphics2D g2d) {

        coordinateOriginX = this.getWidth() / 2;
        coordinateOriginY = this.getHeight() / 2;

        scalingFactorX = this.getSize().width / 1762.0;
        scalingFactorY = this.getSize().height / 1373.0;

        // Achsen zeichnen

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));

        // Ordinatenachse zeichnen
        g2d.drawLine(this.getWidth() / 2, 10, this.getWidth() / 2, this.getHeight() - 10);
        // Abszissenachse zeichnen
        g2d.drawLine(10, this.getHeight() / 2, this.getWidth() - 10, this.getHeight() / 2);

        // linker Pfeil an der Abszisse
        int[] leftArrowX = {20, 10, 20};
        int[] leftArrowY = {this.getHeight() / 2 - 5, this.getHeight() / 2, this.getHeight() / 2 + 5};
        g2d.drawPolyline(leftArrowX, leftArrowY, 3);

        // rechter Pfeil an der Abszisse
        int[] rightArrowX = {this.getWidth() - 20, this.getWidth() - 10, this.getWidth() - 20};
        int[] rightArrowY = {this.getHeight() / 2 - 5, this.getHeight() / 2, this.getHeight() / 2 + 5};
        g2d.drawPolyline(rightArrowX, rightArrowY, 3);

        // oberer Pfeil an der Ordinate
        int[] upperArrowX = {this.getWidth() / 2 - 5, this.getWidth() / 2, this.getWidth() / 2 + 5};
        int[] upperArrowY = {20, 10, 20};
        g2d.drawPolyline(upperArrowX, upperArrowY, 3);

        // unterer Pfeil an der Ordinate
        int[] lowerArrowX = {this.getWidth() / 2 - 5, this.getWidth() / 2, this.getWidth() / 2 + 5};
        int[] lowerArrowY = {this.getHeight() - 20, this.getHeight() - 10, this.getHeight() - 20};
        g2d.drawPolyline(lowerArrowX, lowerArrowY, 3);

        // Gitter zeichnen

        g2d.setStroke(new BasicStroke(1));

        for (int i = coordinateOriginX + (int) (100 * scalingFactorX); i > 10; i -= (int) (100 * scalingFactorX)) {

            g2d.drawLine(i, 10, i, this.getHeight() - 10);
        }

        for (int i = coordinateOriginX + (int) (100 * scalingFactorX); i < this.getWidth(); i += (int) (100 * scalingFactorX)) {

            g2d.drawLine(i, 10, i, this.getHeight() - 10);
        }

        for (int i = coordinateOriginY - (int) (100 * scalingFactorY); i > 10; i -= (int) (100 * scalingFactorY)) {

            g2d.drawLine(10, i, getWidth() - 10, i);
        }

        for (int i = coordinateOriginY + (int) (100 * scalingFactorY); i < this.getHeight() - 10; i += (int) (100 * scalingFactorY)) {

            g2d.drawLine(10, i, getWidth() - 10, i);
        }
    }

    private double function(double x) {
        return ((Math.pow(Math.E, (-Math.pow((x - 2), 2) / 0.05))) + (Math.pow(Math.E, (-Math.pow((x - 5), 2) / 0.1))) + (0.1 * x) - (Math.pow(Math.E, (-Math.pow((x - 3.5), 2) / 0.1))));
    }

    // paintComponent ist Teil des Frameworks und wird durch die Runtime aufgerufen
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Malen von Koordinatensystem mit Hilfslinien und Funktion
        this.drawGrid(g2d);

        // Malen der Funktionskurve
        if (model.isShouldDrawFunction()) {

            this.drawFunction(g2d);
        }

        // Malen der Trapeze aus der Trapezregel
        if (model.isShouldDrawTrapezoid()) {

            this.drawTrapezoidalIntervals(g2d);
        }

        // Malen der adaptiven Intervalle
        if (model.isShouldDrawAdaptiveIntervals()) {

            this.drawAdaptiveIntervals(g2d);
        }
    }
}
