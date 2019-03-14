package approximation.DoneClasses;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

public class Thread {
    int queue;
    double T;
    double lambda;
    Formula formula;
    //Expression expression;
    int numOfPoints;

    public Thread(int queue, double t, double lambda, Formula formula, int numOfPoints) {
        this.queue = queue;
        T = t;
        this.lambda = lambda;
        this.formula = formula;
        this.numOfPoints = numOfPoints;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public void setT(double t) {
        T = t;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public void setNumOfPoints(int numOfPoints) {
        this.numOfPoints = numOfPoints;
    }

    public int getQueue() {
        return queue;
    }

    public double getT() {
        return T;
    }

    public double getLambda() {
        return lambda;
    }

    public Formula getFormula() {
        return formula;
    }

    public int getNumOfPoints() {
        return numOfPoints;
    }

    public static void main(String[] args) {
        Formula formula = new Formula("x^2");
        Thread thread = new Thread(15, 20, 1, formula, 19);
        Intensity intensity = new Intensity(thread.getNumOfPoints(), thread.getT(), thread.getFormula());
        ArrayList<Integer> queues = new ArrayList<>();
        queues.add(thread.getQueue());
            for(int i = 1; i < intensity.getIntervals().size(); i++){
                PoissonDistriburion pd = new PoissonDistriburion(thread.getLambda(),intensity.getIntervals().get(i-1).getLength());
                queues.add(Math.max(0,queues.get(i-1)+ pd.returnNum(pd.u, pd.intervals) - (int)(intensity.getIntervals().get(i-1).getLength()*intensity.getIntensities().get(i-1))));
                System.out.println(queues.get(i-1)+"+"+ pd.returnNum(pd.u, pd.intervals) +"-"+ (int)(intensity.getIntervals().get(i-1).getLength()*intensity.getIntensities().get(i-1)));
        }
        System.out.println("QUEUES: " +queues);
        System.out.println("FINAL: "+ queues.get(queues.size()-1));


    }

}
