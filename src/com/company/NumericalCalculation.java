package com.company;

import java.util.ArrayList;
import java.util.List;

public class NumericalCalculation {

    public static final double LOWER_BOUND = 1.0;
    public static final double UPPER_BOUND = 7.0;
    public static final double EPSILON = 0.001;
    public static List<Integer> refinedIntervals;

    // Diese Funktion ist die Implementierung der Trapezregel
    public double trapezoidalRule(int n) {

        double result = 0.0;
        // Zuerst wird die Groesze der Teilintervalle ermittelt (obere Schranke - untere Schranke / Anzahl der Teilintervalle)
        double distance = (NumericalCalculation.UPPER_BOUND - NumericalCalculation.LOWER_BOUND) / n;
        // a = untere Schranke des Teilintervalls
        double a = NumericalCalculation.LOWER_BOUND;
        // b = obere Schranke des Teilintervalls
        double b = a + distance;

        // Summierung der Flächen der n Trapeze
        for (int i = 0; i < n; i++) {

            // (b - a) * ((f(a) + f(b)) / 2)
            result += (b - a) * ((function(a) + function(b)) / 2);
            // Nachjustierung der Schranken
            a += distance;
            b += distance;
        }

        return result;
    }

    // Diese Funktion ist die Implementierung eines rekursiven adaptiven Algorithmus, welcher aber nicht genutzt wird, da hier keine Anfangsintervalle angegeben werden können
    // Dieser Algorithmus dient nur als Referenz
    public double adaptiveAlgorithmRecursive(double a, double b, int n) {

        double m = (a + b) / 2;
        double iT = (b - a) * ((function(a) + function(b)) / 2);
        double iS = ((iT) + (2 * (b - a)) * function(m) / 3.0);

        if (NumericalCalculation.EPSILON > Math.abs(iT - iS)) {

            return iT;
        } else {

            return adaptiveAlgorithmRecursive(a, m, n) + adaptiveAlgorithmRecursive(m, b, n);
        }
    }

    // Diese Funktion ist die Implementierung eines adaptiven Algrithmus mit n Anfangsintervallen
    public double adaptiveAlgorithm(double a, double b, int n) {

        // Variable für das Endergebnis
        double result = 0.0;

        // Schrittgroesze h
        double h = (b - a) / n;

        // Liste der Schtuetzstellen
        List<Double> xValues = new ArrayList<>();
        for (int i = 0; i <= n; i++) {

            xValues.add(a + i * h);
        }
        // Um eventuell lange Laufzeiten zu verhindern HERAUSNEHMEN
        int itmax = 6;
        // Zaehler wie oft ein Intervall halbiert wurde
        int counter;
        // Initialisierung des Containers für die Anzahl der Verfeinerungen der jeweiligen Intervalle
        refinedIntervals = new ArrayList<>();

        // Ueber alle n Teilintervalle iterieren
        for (int i = 0; i < xValues.size() - 1; i++) {

            // Herausgreifen des ersten Teilintervalls von a bis b (a1 bis b1)
            double an = xValues.get(i);
            double bn = xValues.get(i + 1);
            // Berechnung des Trapez und Simpson-Wertes
            double trapezoidal = h * (function(an) + function(bn) / 2);
            double simpson = (trapezoidal + 2 * h * function((an + bn) / 2)) / 3;
            // Ermitteln der Abweichung des Simpson-Wertes vom Trapezwert
            double difference = Math.abs(trapezoidal - simpson);

            // Initiale Festlegung des Skalierungsfaktors für die Schrittgroesze h der Verfeinerung
            int scaleN = 2;
            // Zaehler nullen
            counter = 0;
            // Container für verfeinerten Trapezwert [0] und verfeinerten Simpsonwert [1]
            double[] res = new double[0];

            // Falls und solange die Differenz nich innerhalb des Toleranzbereiches ist, wird verfeinert
            while ((difference > (bn - an) / (b - a) * Math.max(NumericalCalculation.EPSILON, NumericalCalculation.EPSILON * Math.abs(simpson))) && (counter < itmax)) {

                // Aufrufen der Verfeinerungsfunktion
                res = getComparationValues(an, bn, scaleN);
                // Erneuter Vergleich der Differenz
                difference = Math.abs(res[0] - res[1]);
                // Nochmalige Halbierung der Intervalle, fuer den Fall, dass die Differenz immernoch auszerhalb der Toleranz liegt
                scaleN = 2 * scaleN;
                // Erhoehung des Zaehlers (fuer die grafische Darstellung)
                counter += 1;
            }

            if (counter != 0) {
                result += res[0];
                refinedIntervals.add(counter);
            } else {
                result += trapezoidal;
                refinedIntervals.add(1);
            }
        }

        return result;
    }

    public double[] getComparationValues(double a, double b, int n) {

        double[] result = new double[2];
        // Schrittgroeze h, aendert sich mit jeder Runde
        double h = (b - a) / n;
        // Liste mit neuen Stuetzstellen
        List<Double> xValues = new ArrayList<>();
        for (int i = 0; i <= n; i++) {

            xValues.add(a + i * h);
        }
        result[0] = 0.0;
        result[1] = 0.0;
        double integrant;

        // Berechnung des Trapez- und des Simpson-Wertes für das Teilintervall über die neu erzeugten kleineren Intervalle
        for (int i = 0; i <= n - 1; i++) {

            integrant = h / 2 * (function(xValues.get(i)) + function(xValues.get(i + 1)));
            result[0] += integrant;
            result[1] += (integrant + 2 * h * function((xValues.get(i) + xValues.get(i + 1)) / 2)) / 3;
        }

        return result;
    }

    // Diese Funktion liefert y = f(x) = e^-(x-2)^2/0,05 + e^-(x-5)^2/0,1 + 0,1x - e^-(x-3,5)^2/0,1
    private double function(double x) {
        return ((Math.pow(Math.E, (-Math.pow((x - 2), 2) / 0.05))) + (Math.pow(Math.E, (-Math.pow((x - 5), 2) / 0.1))) + (0.1 * x) - (Math.pow(Math.E, (-Math.pow((x - 3.5), 2) / 0.1))));
    }
}
