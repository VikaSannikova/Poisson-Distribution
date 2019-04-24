package approximation.gui;

import approximation.DoneClasses.Formula;

public class ThreadEX {
    private int id;
    private int queue;
    private double lambda;
    private double greenTime;
    private Formula formula;
    private double yellowTime;

    public ThreadEX(int id, int queue, double lambda, double greenTime, Formula formula, double yellowTime) {
        this.id = id;
        this.queue = queue;
        this.lambda = lambda;
        this.greenTime = greenTime;
        this.formula = formula;
        this.yellowTime = yellowTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public double getGreenTime() {
        return greenTime;
    }

    public void setGreenTime(double greenTime) {
        this.greenTime = greenTime;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public double getYellowTime() {
        return yellowTime;
    }

    public void setYellowTime(double yellowTime) {
        this.yellowTime = yellowTime;
    }
}
