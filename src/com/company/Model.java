package com.company;

public class Model {

    private boolean shouldDrawFunction = false;
    private boolean shouldDrawTrapezoid = false;
    private boolean shouldDrawAdaptiveIntervals = false;

    public boolean isShouldDrawFunction() {
        return shouldDrawFunction;
    }

    public void setShouldDrawFunction(boolean shouldDrawFunction) {
        this.shouldDrawFunction = shouldDrawFunction;
    }

    public boolean isShouldDrawTrapezoid() {
        return shouldDrawTrapezoid;
    }

    public void setShouldDrawTrapezoid(boolean shouldDrawTrapezoid) {
        this.shouldDrawTrapezoid = shouldDrawTrapezoid;
    }

    public boolean isShouldDrawAdaptiveIntervals() {
        return shouldDrawAdaptiveIntervals;
    }

    public void setShouldDrawAdaptiveIntervals(boolean shouldDrawAdaptiveIntervals) {
        this.shouldDrawAdaptiveIntervals = shouldDrawAdaptiveIntervals;
    }
}
